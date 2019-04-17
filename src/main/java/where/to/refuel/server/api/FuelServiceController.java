package where.to.refuel.server.api;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import where.to.refuel.server.model.FuelType;
import where.to.refuel.server.model.Location;
import where.to.refuel.server.model.PetrolStation;
import where.to.refuel.server.service.PetrolStationsService;
import where.to.refuel.server.service.FueloServiceClient;

import javax.inject.Inject;
import java.util.List;

@Controller("/fuel")
public class FuelServiceController {

  private PetrolStationsService petrolStationsService;

  @Inject
  public FuelServiceController(FueloServiceClient fuelPriceService) {
    this.petrolStationsService = fuelPriceService;
  }

  @Get(produces = MediaType.APPLICATION_JSON)
  public List<PetrolStation> index() {
    return petrolStationsService.findByLocationAndFuelType(Location.of(42.6567825, 23.2857181), FuelType.LPG);
  }
}
