package where.to.refuel.server.model;

import lombok.Value;

@Value
public class Coordinates {

  double latitude;
  double longitude;

  private Coordinates(double latitude, double longitude) {
    validateLocation(latitude, longitude);
    this.latitude = latitude;
    this.longitude = longitude;
  }

  /**
   * Creates a new location object, throws an IllegalArgumentException if the lat is greater than 90 and less than -90
   * or Longitude is greater than 180 and less than -180
   *
   * @param latitude  double between -90 and 90
   * @param longitude double between -180 and 180
   * @return Location
   */
  public static Coordinates of(double latitude, double longitude) {
    return new Coordinates(latitude, longitude);
  }

  private static void validateLocation(double latitude, double longitude) {
    if (latitude < -90 || latitude > 90) {
      throw new IllegalArgumentException("Latitude should be a number between -90 and 90.");
    }

    if (longitude < -180 || longitude > 180) {
      throw new IllegalArgumentException("Longitude should be a number between -180 and 180.");
    }
  }
}
