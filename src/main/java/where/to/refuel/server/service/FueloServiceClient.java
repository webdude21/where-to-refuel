package where.to.refuel.server.service;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.uri.UriTemplate;
import io.reactivex.Flowable;
import where.to.refuel.server.dto.NearByPetrolStationsRequestTO;
import where.to.refuel.server.dto.PetrolStationTO;
import where.to.refuel.server.dto.PetrolStationsResponseTO;
import where.to.refuel.server.model.Coordinates;
import where.to.refuel.server.model.FuelType;
import where.to.refuel.server.model.PetrolStation;

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

  @Inject
  public FueloServiceClient(@Client("fuelo") RxHttpClient httpClient, DrivingInformationService drivingInformationService) {
    this.httpClient = httpClient;
    this.drivingInformationService = drivingInformationService;
  }

  @Override
  public List<PetrolStation> findByLocationAndFuelType(Coordinates coordinates, FuelType fuelType) {
    var requestTO = NearByPetrolStationsRequestTO.of(coordinates, fuelType);
    var requestUri = UriTemplate.of("/api/near?lat={latitude}&lon={longitude}&fuel={fuel}").expand(requestTO);
    var result = httpClient.retrieve(HttpRequest.GET(requestUri), PetrolStationsResponseTO.class);

    var petrolStations = Optional.ofNullable(result.blockingFirst().getPetrolStationTOS())
      .stream()
      .flatMap(Collection::stream)
      .map(PetrolStationTO::toPetrolStation)
      .collect(Collectors.toList());

    return drivingInformationService.findDrivingInformationFor(coordinates, petrolStations);
  }
}
