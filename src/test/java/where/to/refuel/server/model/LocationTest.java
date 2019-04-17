package where.to.refuel.server.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationTest {

  @Test
  void tryingToCreateAnInvalidLocationThrows() {
    assertThrows(IllegalArgumentException.class, () -> Location.of(-92, 211));
  }

  @Test
  void creatingValidLocationCorrectlyCreated() {
    var location = Location.of(21.2123, 31.3215);
    assertNotNull(location);
    assertEquals(21.2123, location.latitude);
    assertEquals(31.3215, location.longitude);
  }

  @Test
  void testThatToStringWorks() {
    var location = Location.of(21.2123, 31.3215);
    assertEquals("Location(latitude=21.2123, longitude=31.3215)", location.toString());
  }
}
