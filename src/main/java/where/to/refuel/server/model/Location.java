package where.to.refuel.server.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Location {
  private final int id;
  private final String name;
  private final String city;
  private final String address;
  private final Coordinates coordinates;
  private final double absoluteDistance;
  private DrivingInfo drivingInfo;
}
