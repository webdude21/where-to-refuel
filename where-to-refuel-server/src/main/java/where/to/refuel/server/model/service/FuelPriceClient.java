package where.to.refuel.server.model.service;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.reactivex.Single;
import where.to.refuel.server.dto.NearByPetrolStationsPriceRequestTO;
import where.to.refuel.server.model.Coordinates;
import where.to.refuel.server.model.FuelType;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Map;

@Singleton
public class FuelPriceClient implements PetrolStationPriceService {

  private final RxHttpClient httpClient;

  @Inject
  public FuelPriceClient(@Client("fuelowebsite") RxHttpClient httpClient) {
    this.httpClient = httpClient;
  }

  @Override
  public Single<Map<Integer, Double>> findByLocationAndFuelType(Coordinates coordinates, FuelType fuelType) {
    var requestTO = NearByPetrolStationsPriceRequestTO.of(coordinates, fuelType);
    var mutableHttpRequest = HttpRequest.POST("/gasstations/ajax_get_near_gasstations_fuel_type", requestTO)
      .contentType(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
      .accept(MediaType.TEXT_PLAIN_TYPE);

    return httpClient.retrieve(mutableHttpRequest, String.class)
      .firstOrError()
      .map(PetrolStationsByFuelTypeHtmlParser::parse);
  }
}
