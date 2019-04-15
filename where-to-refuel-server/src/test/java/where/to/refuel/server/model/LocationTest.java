package where.to.refuel.server.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationTest {

  @Test
  void tryingToCreateAnInvalidLocationTrhows() {
    assertThrows(IllegalArgumentException.class, () -> Location.of(-92, 211));
  }
}
