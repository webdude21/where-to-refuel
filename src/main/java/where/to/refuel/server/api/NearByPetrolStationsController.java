package where.to.refuel.server.api;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import where.to.refuel.server.dto.NearByPetrolStationsRequestTO;
import where.to.refuel.server.model.Coordinates;
import where.to.refuel.server.model.FuelType;
import where.to.refuel.server.model.PetrolStation;
import where.to.refuel.server.model.service.FueloServiceClient;
import where.to.refuel.server.model.service.PetrolStationsService;

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

  @Get(produces = MediaType.APPLICATION_JSON)
  public List<PetrolStation> findNearByPetrolStations(@QueryValue double latitude, @QueryValue double longitude, @QueryValue FuelType fuel) {
    var location = Coordinates.of(latitude, longitude);
    return petrolStationsService.findByLocationAndFuelType(location, fuel);
  }
}
