package where.to.refuel.server.model.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class PetrolStationsByFuelTypeHtmlParserTest {

  private static String testData;

  @BeforeAll
  static void setup() throws IOException {
    ClassLoader classLoader = PetrolStationsByFuelTypeHtmlParserTest.class.getClassLoader();
    Path path = Paths.get(classLoader.getResource("near_gasstations_fuel_type.html").getPath());
    testData = Files.readString(path);
  }

  @Test
  void parse() {
    Assertions.assertNotNull(PetrolStationsByFuelTypeHtmlParser.parse(testData));
  }
}
