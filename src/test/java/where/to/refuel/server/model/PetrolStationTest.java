package where.to.refuel.server.model;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

class PetrolStationTest {

  @Test
  void hasValidDistanceShouldReturnFalseWhenNoDrivingInformation() {
    PetrolStation petrolStation = new PetrolStation(1, "OMV", "Ямбол", "Ст",
      Coordinates.of(42.6567825, 23.2857181), 2, Brand.of(2, "OMV"));

    assertFalse(PetrolStation.hasValidDistance(petrolStation));
  }

  @Test
  void hasValidDistanceShouldReturnFalseWhenDrivingDistanceIsLessThanTheAbsoluteDistance() {
    PetrolStation petrolStation = new PetrolStation(1, "OMV", "Ямбол", "Ст",
      Coordinates.of(42.6567825, 23.2857181), 14.2, Brand.of(2, "OMV"));

    petrolStation.setDrivingInfo(DrivingInfo.of(14, 12.12));

    assertFalse(PetrolStation.hasValidDistance(petrolStation));
  }

  @Test
  void hasValidDistanceShouldReturnTrueWhenDrivingDistanceIsGreaterThanTheAbsoluteDistance() {
    PetrolStation petrolStation = new PetrolStation(1, "OMV", "Ямбол", "Ст",
      Coordinates.of(42.6567825, 23.2857181), 2, Brand.of(2, "OMV"));

    petrolStation.setDrivingInfo(DrivingInfo.of(14, 12.12));

    assertTrue(PetrolStation.hasValidDistance(petrolStation));
  }
}
