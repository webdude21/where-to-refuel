package where.to.refuel.server.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Location {
  private int id;
  private String name;
  private String city;
  private String address;
  private Coordinates coordinates;
  private double absoluteDistance;
  private DrivingInfo drivingInfo;

  public Location(int id, String name, String city, String address, Coordinates coordinates, double absoluteDistance) {
    this.id = id;
    this.name = name;
    this.city = city;
    this.address = address;
    this.coordinates = coordinates;
    this.absoluteDistance = absoluteDistance;
  }
}
