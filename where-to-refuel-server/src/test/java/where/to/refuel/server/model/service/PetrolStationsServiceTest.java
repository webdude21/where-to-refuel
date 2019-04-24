package where.to.refuel.server.model.service;

import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import where.to.refuel.server.model.Coordinates;
import where.to.refuel.server.model.FuelType;
import where.to.refuel.server.model.PetrolStation;

import javax.inject.Inject;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@MicronautTest
class PetrolStationsServiceTest extends IntegrationTest {

  private static final Coordinates VALID_COORDINATES = Coordinates.of(42.6567825, 23.2857181);
  private static final Coordinates INVALID_COORDINATES = Coordinates.of(0.12, 0.213);

  @Inject
  private PetrolStationsService service;

  @Test
  void findByLocationShouldReturnValidResults() {
    List<PetrolStation> result = service.findByLocationAndFuelType(VALID_COORDINATES, FuelType.LPG).toList().blockingGet();
    assertNotNull(result);
    assertThat(result, hasSize(50));
  }

  @Test
  void findByLocationShouldReturnEmptyListIfNoPetrolStationsAreFound() {
    List<PetrolStation> result = service.findByLocationAndFuelType(INVALID_COORDINATES, FuelType.LPG).toList().blockingGet();
    assertNotNull(result);
    assertThat(result, empty());
  }
}
