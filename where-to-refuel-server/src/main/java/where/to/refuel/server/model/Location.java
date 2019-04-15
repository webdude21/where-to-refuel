package where.to.refuel.server.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class Location {

  final double latitude;
  final double longitude;

  private Location(double latitude, double longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public static Location of(double latitude, double longitude) {
    var location = new Location(latitude, longitude);
    validateLocation(location);
    return location;
  }

  private static void validateLocation(Location location) {
    double latitude = location.getLatitude();
    double longitude = location.getLongitude();

    if (latitude < -90 || latitude > 90) {
      throw new IllegalArgumentException("Latitude should be a number between -90 and 90.");
    }

    if (longitude < -180 || latitude > 180) {
      throw new IllegalArgumentException("Longitude should be a number between -180 and 180.");
    }
  }
}
