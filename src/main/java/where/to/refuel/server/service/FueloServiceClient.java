package where.to.refuel.server.service;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.uri.UriTemplate;
import io.reactivex.Flowable;
import where.to.refuel.server.dto.NearByPetrolStationsRequestTO;
import where.to.refuel.server.dto.PetrolStationTO;
import where.to.refuel.server.dto.PetrolStationsResponseTO;
import where.to.refuel.server.model.FuelType;
import where.to.refuel.server.model.Location;
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

  @Inject
  public FueloServiceClient(@Client("fuelo") RxHttpClient httpClient) {
    this.httpClient = httpClient;
  }

  @Override
  public List<PetrolStation> findByLocationAndFuelType(Location location, FuelType fuelType) {
    var requestTO = NearByPetrolStationsRequestTO.of(location, fuelType);
    var requestUri = UriTemplate.of("/api/near?lat={latitude}&lon={longitude}&fuel={fuel}").expand(requestTO);
    Flowable<PetrolStationsResponseTO> result = httpClient.retrieve(HttpRequest.GET(requestUri), PetrolStationsResponseTO.class);
    return Optional.ofNullable(result.blockingFirst().getPetrolStationTOS())
      .stream()
      .flatMap(Collection::stream)
      .map(PetrolStationTO::toPetrolStation)
      .collect(Collectors.toList());
  }
}
