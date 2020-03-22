package where.to.refuel.server.model.service;

import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import where.to.refuel.server.model.Coordinates;
import where.to.refuel.server.model.Location;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@MicronautTest
class BingRouteServiceTest extends IntegrationTest {

  private static final Coordinates VALID_COORDINATES = Coordinates.of(42.6567825, 23.2857181);
  private static final List<Coordinates> TEST_COORDINATES = Arrays.asList(
    Coordinates.of(42.65147, 23.287512),
    Coordinates.of(42.650642, 23.289873),
    Coordinates.of(42.6618, 23.295099),
    Coordinates.of(42.650101, 23.2931),
    Coordinates.of(42.659534, 23.298437),
    Coordinates.of(42.658001, 23.2717),
    Coordinates.of(42.66748, 23.280899),
    Coordinates.of(42.667545, 23.290752),
    Coordinates.of(42.659054, 23.305847),
    Coordinates.of(42.663986, 23.260677),
    Coordinates.of(42.674503, 23.270239),
    Coordinates.of(42.651318, 23.316212),
    Coordinates.of(42.659698, 23.322701),
    Coordinates.of(42.680866, 23.308319),
    Coordinates.of(42.638401, 23.3193),
    Coordinates.of(42.63879, 23.320749),
    Coordinates.of(42.687611, 23.275389),
    Coordinates.of(42.688942, 23.276165),
    Coordinates.of(42.689621, 23.276772),
    Coordinates.of(42.668171, 23.328953),
    Coordinates.of(42.672379, 23.24338),
    Coordinates.of(42.691101, 23.295601),
    Coordinates.of(42.658298, 23.336399),
    Coordinates.of(42.693901, 23.2934),
    Coordinates.of(42.694431, 23.294298),
    Coordinates.of(42.657799, 23.339199),
    Coordinates.of(42.682217, 23.242872),
    Coordinates.of(42.636799, 23.334499),
    Coordinates.of(42.697498, 23.2962),
    Coordinates.of(42.690102, 23.2509),
    Coordinates.of(42.689301, 23.2493),
    Coordinates.of(42.683674, 23.240582),
    Coordinates.of(42.654499, 23.2276),
    Coordinates.of(42.66, 23.348),
    Coordinates.of(42.702301, 23.2974),
    Coordinates.of(42.703148, 23.297472),
    Coordinates.of(42.651173, 23.218575),
    Coordinates.of(42.650902, 23.2178),
    Coordinates.of(42.703602, 23.3109),
    Coordinates.of(42.6656, 23.354099),
    Coordinates.of(42.706299, 23.306),
    Coordinates.of(42.62875, 23.345062),
    Coordinates.of(42.705185, 23.311218),
    Coordinates.of(42.628502, 23.3459),
    Coordinates.of(42.651012, 23.357609),
    Coordinates.of(42.649258, 23.214113),
    Coordinates.of(42.691101, 23.229799),
    Coordinates.of(42.6632, 23.358299),
    Coordinates.of(42.710648, 23.272182),
    Coordinates.of(42.7117, 23.268801)
  );

  @Inject
  private BingRouteService bingRouteService;

  @Test
  void findDrivingInformationFor() {
    var testLocations = IntStream.range(0, TEST_COORDINATES.size())
      .mapToObj(this::createTestLocation)
      .collect(Collectors.toList());

    var actual = bingRouteService.findDrivingInformationFor(VALID_COORDINATES, testLocations).toList().blockingGet();

    assertNotNull(actual);
    actual.forEach(location -> assertNotNull(location.getDrivingInfo()));
    assertThat(actual, hasSize(50));
    assertIterableEquals(testLocations, actual);
  }

  private Location createTestLocation(int index) {
    return new Location(index,
      "Petrol Station " + index,
      "Sofia",
      "My Street",
      TEST_COORDINATES.get(index),
      21.12
    );
  }
}
