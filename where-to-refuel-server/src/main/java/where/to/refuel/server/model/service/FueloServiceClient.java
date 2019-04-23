package where.to.refuel.server.model.service;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.uri.UriTemplate;
import where.to.refuel.server.dto.NearByPetrolStationsRequestTO;
import where.to.refuel.server.dto.PetrolStationTO;
import where.to.refuel.server.dto.PetrolStationsResponseTO;
import where.to.refuel.server.model.Coordinates;
import where.to.refuel.server.model.FuelType;
import where.to.refuel.server.model.PetrolStation;
import where.to.refuel.server.model.PriceInformation;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Singleton
public class FueloServiceClient implements PetrolStationsService {
  private final RxHttpClient httpClient;
  private final DrivingInformationService drivingInformationService;
  private PetrolStationPriceService petrolStationPriceService;

  @Inject
  public FueloServiceClient(@Client("fuelo") RxHttpClient httpClient,
                            PetrolStationPriceService petrolStationPriceService,
                            DrivingInformationService drivingInformationService) {
    this.httpClient = httpClient;
    this.petrolStationPriceService = petrolStationPriceService;
    this.drivingInformationService = drivingInformationService;
  }

  @Override
  public List<PetrolStation> findByLocationAndFuelType(Coordinates coordinates, FuelType fuelType) {
    var requestTO = NearByPetrolStationsRequestTO.of(coordinates, fuelType);
    var requestUri = UriTemplate.of("/api/near?lat={latitude}&lon={longitude}&fuel={fuel}&limit={limit}").expand(requestTO);
    var result = httpClient.retrieve(HttpRequest.GET(requestUri), PetrolStationsResponseTO.class);

    var pricesMap = petrolStationPriceService.findByLocationAndFuelType(coordinates, fuelType);

    var petrolStations = Optional.ofNullable(result.blockingFirst().getPetrolStationTOS())
      .stream()
      .flatMap(Collection::stream)
      .map(PetrolStationTO::toPetrolStation)
      .collect(Collectors.toList());

    var petrolStationsWithDrivingInfo = drivingInformationService.findDrivingInformationFor(coordinates, petrolStations);

    return petrolStationsWithDrivingInfo.stream()
      .filter(PetrolStation::hasValidDistance)
      .peek(petrolStation -> petrolStation.setPriceInformation(PriceInformation.of(fuelType, pricesMap.get(petrolStation.getId()))))
      .collect(Collectors.toList());
  }
}
