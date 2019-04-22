package where.to.refuel.server.model.service;

import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import where.to.refuel.server.model.Coordinates;
import where.to.refuel.server.model.FuelType;

import javax.inject.Inject;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.aMapWithSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@MicronautTest
class PetrolStationPriceServiceTest extends IntegrationTest {

  private static final Coordinates VALID_COORDINATES = Coordinates.of(42.6567825, 23.2857181);

  @Inject
  private PetrolStationPriceService service;

  @Test
  void findByLocationShouldReturnValidResults() {
    Map<Integer, Double> result = service.findByLocationAndFuelType(VALID_COORDINATES, FuelType.LPG);
    assertNotNull(result);
    assertThat(result, aMapWithSize(50));
  }
}
