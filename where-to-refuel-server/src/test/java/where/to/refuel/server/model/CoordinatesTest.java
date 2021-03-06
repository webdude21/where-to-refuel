package where.to.refuel.server.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoordinatesTest {

  @Test
  void tryingToCreateAnInvalidCoordinatesThrows() {
    assertThrows(IllegalArgumentException.class, () -> Coordinates.of(-92, 211));
  }

  @Test
  void creatingValidCoordinatesCorrectlyCreated() {
    var coordinates = Coordinates.of(21.2123, 31.3215);
    assertNotNull(coordinates);
    assertEquals(21.2123, coordinates.getLatitude());
    assertEquals(31.3215, coordinates.getLongitude());
  }

  @Test
  void testThatToStringWorks() {
    var coordinates = Coordinates.of(21.2123, 31.3215);
    assertEquals("Coordinates(latitude=21.2123, longitude=31.3215)", coordinates.toString());
  }
}
