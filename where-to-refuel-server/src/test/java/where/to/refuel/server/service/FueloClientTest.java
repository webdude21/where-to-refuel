package where.to.refuel.server.service;

import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import where.to.refuel.server.model.FuelType;
import where.to.refuel.server.model.Location;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class FueloClientTest {

  private static final Location VALID_LOCATION = Location.of(12.1212, 24.42141);

  @Inject
  private FuelPriceService fuelPriceService;

  @Test
  void findByLocationShouldNotReturnNull(){
    assertNotNull(fuelPriceService.findByLocationAndFuelType(VALID_LOCATION, FuelType.PETROL));
  }
}
