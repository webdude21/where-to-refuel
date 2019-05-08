package where.to.refuel.server.api;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.reactivex.Flowable;
import where.to.refuel.server.dto.NearByPetrolStationsRequestTO;
import where.to.refuel.server.model.Coordinates;
import where.to.refuel.server.model.FuelType;
import where.to.refuel.server.model.PetrolStation;
import where.to.refuel.server.model.service.FueloServiceClient;
import where.to.refuel.server.model.service.PetrolStationsService;
import where.to.refuel.server.model.service.UserLogService;

import javax.inject.Inject;

import static io.micronaut.http.HttpHeaders.CACHE_CONTROL;
import static io.micronaut.http.HttpResponse.ok;
import static where.to.refuel.server.util.HttpUtils.extractIpAddress;

@Controller("/near-by-petrol-stations")
public class NearByPetrolStationsController {

  private static final String CACHE_FOR_6_HOURS = "public, immutable, max-age=21600";
  private PetrolStationsService petrolStationsService;
  private UserLogService userLogService;

  @Inject
  public NearByPetrolStationsController(FueloServiceClient fuelPriceService, UserLogService userLogService) {
    this.petrolStationsService = fuelPriceService;
    this.userLogService = userLogService;
  }

  @Get(value = "/{?requestTO*}", produces = MediaType.APPLICATION_JSON)
  public HttpResponse<Flowable<PetrolStation>> findNearByPetrolStations(final NearByPetrolStationsRequestTO requestTO,
                                                                        final HttpRequest<?> httpRequest) {
    var location = Coordinates.of(requestTO.getLatitude(), requestTO.getLongitude());
    var fuelType = FuelType.valueOf(requestTO.getFuel());
    userLogService.logUserInfo(location, fuelType, extractIpAddress(httpRequest));
    var result = petrolStationsService.findByLocationAndFuelType(location, fuelType);
    return ok(result).header(CACHE_CONTROL, CACHE_FOR_6_HOURS);
  }
}
