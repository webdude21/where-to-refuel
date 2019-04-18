package where.to.refuel.server.api;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import where.to.refuel.server.dto.NearByPetrolStationsRequestTO;
import where.to.refuel.server.model.Coordinates;
import where.to.refuel.server.model.FuelType;
import where.to.refuel.server.model.PetrolStation;
import where.to.refuel.server.service.FueloServiceClient;
import where.to.refuel.server.service.PetrolStationsService;

import javax.inject.Inject;
import java.util.List;

@Controller("/near-by-petrol-stations")
public class NearByPetrolStationsController {

  private PetrolStationsService petrolStationsService;

  @Inject
  public NearByPetrolStationsController(FueloServiceClient fuelPriceService) {
    this.petrolStationsService = fuelPriceService;
  }

  @Post(produces = MediaType.APPLICATION_JSON)
  public List<PetrolStation> findNearByPetrolStations(@Body NearByPetrolStationsRequestTO requestTO) {
    var location = Coordinates.of(requestTO.getLatitude(), requestTO.getLongitude());
    return petrolStationsService.findByLocationAndFuelType(location, FuelType.valueOf(requestTO.getFuel()));
  }
}
