package where.to.refuel.server.model.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.aMapWithSize;
import static org.hamcrest.Matchers.is;

class PetrolStationsByFuelTypeHtmlParserTest {

  private static String testData;

  @BeforeAll
  static void setup() throws IOException, URISyntaxException {
    ClassLoader classLoader = PetrolStationsByFuelTypeHtmlParserTest.class.getClassLoader();
    Path path = Paths.get(Objects.requireNonNull(classLoader.getResource("near_gasstations_fuel_type.html")).toURI());
    testData = Files.readString(path);
  }

  @Test
  void parseShouldExtractTheIdToPriceMapping() {
    var expectedIdToPriceMap = Map.ofEntries(
      Map.entry(640, 0.99),
      Map.entry(370, 0.97),
      Map.entry(1804, 0.97),
      Map.entry(211, 0.99),
      Map.entry(1304, 0.97),
      Map.entry(567, 0.97),
      Map.entry(1876, 0.95),
      Map.entry(632, 0.97),
      Map.entry(43629, 0.94),
      Map.entry(235, 0.99),
      Map.entry(1878, 0.94),
      Map.entry(267, 0.99),
      Map.entry(1408, 1.02),
      Map.entry(272, 0.97),
      Map.entry(214, 1.00),
      Map.entry(308, 0.98),
      Map.entry(396, 0.98),
      Map.entry(2105, 0.96),
      Map.entry(593, 0.97),
      Map.entry(208, 0.99),
      Map.entry(181, 0.97),
      Map.entry(623, 0.97),
      Map.entry(2106, 0.93),
      Map.entry(365, 0.97),
      Map.entry(164, 0.97),
      Map.entry(171, 0.97),
      Map.entry(618, 0.92),
      Map.entry(1306, 0.97),
      Map.entry(246, 0.97),
      Map.entry(1501, 0.96),
      Map.entry(1500, 0.92),
      Map.entry(1943, 0.89),
      Map.entry(283, 0.97),
      Map.entry(303, 0.97),
      Map.entry(175, 0.97),
      Map.entry(1280, 1.01),
      Map.entry(2016, 0.93),
      Map.entry(170, 1.02),
      Map.entry(610, 1.02),
      Map.entry(1294, 0.97),
      Map.entry(317, 1.00),
      Map.entry(625, 0.97),
      Map.entry(1492, 1.00),
      Map.entry(290, 0.97),
      Map.entry(637, 0.97),
      Map.entry(582, 0.95),
      Map.entry(178, 1.05),
      Map.entry(571, 1.00),
      Map.entry(590, 0.97),
      Map.entry(173, 0.97));

    var parsedIdToPriceMap = PetrolStationsByFuelTypeHtmlParser.parse(testData);

    assertThat(parsedIdToPriceMap, aMapWithSize(50));
    assertThat(expectedIdToPriceMap, is(parsedIdToPriceMap));
  }
}
