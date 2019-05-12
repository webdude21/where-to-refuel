package where.to.refuel.server.model.service;

import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import where.to.refuel.server.model.Coordinates;
import where.to.refuel.server.model.Location;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@MicronautTest
class BingRouteServiceTest extends IntegrationTest {

  private static final Coordinates VALID_COORDINATES = Coordinates.of(42.6567825, 23.2857181);

  @Inject
  private BingRouteService bingRouteService;

  @Test
  void findDrivingInformationFor() {
    var input = Arrays.asList(
      new Location(1, "Test Location 1", "Sofia", "Test Address 1", Coordinates.of(42.65147, 23.287512), 10),
      new Location(2, "Test Location 2", "Sofia", "Test Address 2", Coordinates.of(42.650642, 23.289873), 10),
      new Location(3, "Test Location 3", "Sofia", "Test Address 3", Coordinates.of(42.6618, 23.295099), 10),
      new Location(4, "Test Location 4", "Sofia", "Test Address 4", Coordinates.of(42.650101, 23.2931), 10),
      new Location(5, "Test Location 5", "Sofia", "Test Address 5", Coordinates.of(42.659534, 23.298437), 10),
      new Location(6, "Test Location 6", "Sofia", "Test Address 6", Coordinates.of(42.658001, 23.2717), 10),
      new Location(7, "Test Location 7", "Sofia", "Test Address 7", Coordinates.of(42.66748, 23.280899), 10),
      new Location(8, "Test Location 8", "Sofia", "Test Address 8", Coordinates.of(42.667545, 23.290752), 10),
      new Location(9, "Test Location 9", "Sofia", "Test Address 9", Coordinates.of(42.659054, 23.305847), 10),
      new Location(10, "Test Location 10", "Sofia", "Test Address 10", Coordinates.of(42.663986, 23.260677), 10),
      new Location(11, "Test Location 11", "Sofia", "Test Address 11", Coordinates.of(42.674503, 23.270239), 10),
      new Location(12, "Test Location 12", "Sofia", "Test Address 12", Coordinates.of(42.651318, 23.316212), 10),
      new Location(13, "Test Location 13", "Sofia", "Test Address 13", Coordinates.of(42.659698, 23.322701), 10),
      new Location(14, "Test Location 14", "Sofia", "Test Address 14", Coordinates.of(42.680866, 23.308319), 10),
      new Location(15, "Test Location 15", "Sofia", "Test Address 15", Coordinates.of(42.638401, 23.3193), 10),
      new Location(16, "Test Location 16", "Sofia", "Test Address 16", Coordinates.of(42.63879, 23.320749), 10),
      new Location(17, "Test Location 17", "Sofia", "Test Address 17", Coordinates.of(42.687611, 23.275389), 10),
      new Location(18, "Test Location 18", "Sofia", "Test Address 18", Coordinates.of(42.688942, 23.276165), 10),
      new Location(19, "Test Location 19", "Sofia", "Test Address 19", Coordinates.of(42.689621, 23.276772), 10),
      new Location(20, "Test Location 20", "Sofia", "Test Address 20", Coordinates.of(42.668171, 23.328953), 10),
      new Location(21, "Test Location 21", "Sofia", "Test Address 21", Coordinates.of(42.672379, 23.24338), 10),
      new Location(22, "Test Location 22", "Sofia", "Test Address 22", Coordinates.of(42.691101, 23.295601), 10),
      new Location(23, "Test Location 23", "Sofia", "Test Address 23", Coordinates.of(42.658298, 23.336399), 10),
      new Location(24, "Test Location 24", "Sofia", "Test Address 24", Coordinates.of(42.693901, 23.2934), 10),
      new Location(25, "Test Location 25", "Sofia", "Test Address 25", Coordinates.of(42.694431, 23.294298), 10),
      new Location(26, "Test Location 26", "Sofia", "Test Address 26", Coordinates.of(42.657799, 23.339199), 10),
      new Location(27, "Test Location 27", "Sofia", "Test Address 27", Coordinates.of(42.682217, 23.242872), 10),
      new Location(28, "Test Location 28", "Sofia", "Test Address 28", Coordinates.of(42.636799, 23.334499), 10),
      new Location(29, "Test Location 29", "Sofia", "Test Address 29", Coordinates.of(42.697498, 23.2962), 10),
      new Location(30, "Test Location 30", "Sofia", "Test Address 30", Coordinates.of(42.690102, 23.2509), 10),
      new Location(31, "Test Location 31", "Sofia", "Test Address 31", Coordinates.of(42.689301, 23.2493), 10),
      new Location(32, "Test Location 32", "Sofia", "Test Address 32", Coordinates.of(42.683674, 23.240582), 10),
      new Location(33, "Test Location 33", "Sofia", "Test Address 33", Coordinates.of(42.654499, 23.2276), 10),
      new Location(34, "Test Location 34", "Sofia", "Test Address 34", Coordinates.of(42.66, 23.348), 10),
      new Location(35, "Test Location 35", "Sofia", "Test Address 35", Coordinates.of(42.702301, 23.2974), 10),
      new Location(36, "Test Location 36", "Sofia", "Test Address 36", Coordinates.of(42.703148, 23.297472), 10),
      new Location(37, "Test Location 37", "Sofia", "Test Address 37", Coordinates.of(42.651173, 23.218575), 10),
      new Location(38, "Test Location 38", "Sofia", "Test Address 38", Coordinates.of(42.650902, 23.2178), 10),
      new Location(39, "Test Location 39", "Sofia", "Test Address 39", Coordinates.of(42.703602, 23.3109), 10),
      new Location(40, "Test Location 40", "Sofia", "Test Address 40", Coordinates.of(42.6656, 23.354099), 10),
      new Location(41, "Test Location 41", "Sofia", "Test Address 41", Coordinates.of(42.706299, 23.306), 10),
      new Location(42, "Test Location 42", "Sofia", "Test Address 42", Coordinates.of(42.62875, 23.345062), 10),
      new Location(43, "Test Location 43", "Sofia", "Test Address 43", Coordinates.of(42.705185, 23.311218), 10),
      new Location(44, "Test Location 44", "Sofia", "Test Address 44", Coordinates.of(42.628502, 23.3459), 10),
      new Location(45, "Test Location 45", "Sofia", "Test Address 45", Coordinates.of(42.651012, 23.357609), 10),
      new Location(46, "Test Location 46", "Sofia", "Test Address 46", Coordinates.of(42.649258, 23.214113), 10),
      new Location(47, "Test Location 47", "Sofia", "Test Address 47", Coordinates.of(42.691101, 23.229799), 10),
      new Location(48, "Test Location 48", "Sofia", "Test Address 48", Coordinates.of(42.6632, 23.358299), 10),
      new Location(49, "Test Location 49", "Sofia", "Test Address 49", Coordinates.of(42.710648, 23.272182), 10),
      new Location(50, "Test Location 50", "Sofia", "Test Address 50", Coordinates.of(42.7117, 23.268801), 10)
    );

    input.forEach(location -> assertNull(location.getDrivingInfo()));

    var actual = bingRouteService.findDrivingInformationFor(VALID_COORDINATES, input).toList().blockingGet();

    assertNotNull(actual);
    assertThat(actual, hasSize(50));
    IntStream.range(0, input.size()).forEach(i -> assertThat(input.get(i), equalTo(actual.get(i))));
    actual.forEach(location -> assertNotNull(location.getDrivingInfo()));
  }
}
