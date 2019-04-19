package where.to.refuel.server.model.service;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import where.to.refuel.server.model.Coordinates;
import where.to.refuel.server.model.FuelType;
import where.to.refuel.server.model.PetrolStation;

import javax.inject.Inject;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@MicronautTest
class FuelServiceTest {

  private static final Coordinates VALID_COORDINATES = Coordinates.of(42.6567825, 23.2857181);
  private static final Coordinates INVALID_COORDINATES = Coordinates.of(0.12, 0.213);

  @Rule
  public static WireMockRule wireMock = new WireMockRule(8090);

  @Inject
  private PetrolStationsService petrolStationsService;

  @BeforeAll
  static void setup() {
    wireMock.start();
  }

  @Test
  void findByLocationShouldReturnValidResults() {
    List<PetrolStation> result = petrolStationsService.findByLocationAndFuelType(VALID_COORDINATES, FuelType.LPG);
    assertNotNull(result);
    assertEquals(50, result.size());
  }

  @Test
  void findByLocationShouldReturnEmptyListIfNoPetrolStationsAreFound() {
    List<PetrolStation> result = petrolStationsService.findByLocationAndFuelType(INVALID_COORDINATES, FuelType.LPG);
    assertNotNull(result);
    assertEquals(0, result.size());
  }
}
