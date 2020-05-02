package where.to.refuel.server.model.service;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.uri.UriTemplate;
import io.reactivex.Flowable;
import io.reactivex.Single;
import where.to.refuel.server.dto.NearByPetrolStationsRequestTO;
import where.to.refuel.server.dto.PetrolStationTO;
import where.to.refuel.server.dto.PetrolStationsResponseTO;
import where.to.refuel.server.model.Coordinates;
import where.to.refuel.server.model.FuelType;
import where.to.refuel.server.model.PetrolStation;
import where.to.refuel.server.model.PriceInformation;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Map;

@Singleton
public class FueloServiceClient implements PetrolStationsService {
  private final RxHttpClient httpClient;
  private final DrivingInformationService drivingInformationService;
  private final PetrolStationPriceService petrolStationPriceService;

  @Inject
  public FueloServiceClient(@Client("fuelo") RxHttpClient httpClient,
                            PetrolStationPriceService petrolStationPriceService,
                            DrivingInformationService drivingInformationService) {
    this.httpClient = httpClient;
    this.petrolStationPriceService = petrolStationPriceService;
    this.drivingInformationService = drivingInformationService;
  }

  @Override
  public Flowable<PetrolStation> findByLocationAndFuelType(Coordinates coordinates, FuelType fuelType) {
    var nearByPetrolStations = getNearByPetrolStations(coordinates, fuelType);
    var pricesMapSingle = petrolStationPriceService.findByLocationAndFuelType(coordinates, fuelType);
    return nearByPetrolStations
      .zipWith(pricesMapSingle, (ps, priceMap) -> mergeResults(ps, coordinates, priceMap, fuelType))
      .flattenAsFlowable(Flowable::blockingIterable);
  }

  private Single<List<PetrolStation>> getNearByPetrolStations(Coordinates origin, FuelType fuelType) {
    var requestTO = NearByPetrolStationsRequestTO.of(origin, fuelType);
    var requestUri = UriTemplate.of("/api/near?lat={latitude}&lon={longitude}&fuel={fuel}&limit={limit}").expand(requestTO);
    return httpClient.retrieve(HttpRequest.GET(requestUri), PetrolStationsResponseTO.class)
      .firstOrError()
      .map(PetrolStationsResponseTO::getPetrolStationTOS)
      .flattenAsFlowable(petrolStationTOS -> petrolStationTOS)
      .map(PetrolStationTO::toPetrolStation)
      .toList();
  }

  private Flowable<PetrolStation> mergeResults(List<PetrolStation> ps, Coordinates origin, Map<Integer, Double> priceMap, FuelType fuelType) {
    return drivingInformationService.findDrivingInformationFor(origin, ps)
      .filter(PetrolStation::hasValidDistance)
      .map(petrolStation -> petrolStation.setPriceInformation(PriceInformation.of(fuelType, priceMap.get(petrolStation.getId()))));
  }
}
