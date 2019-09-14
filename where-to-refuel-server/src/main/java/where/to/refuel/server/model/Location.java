package where.to.refuel.server.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Location {
  final int id;
  final String name;
  final String city;
  final String address;
  final Coordinates coordinates;
  final double absoluteDistance;
  DrivingInfo drivingInfo;
}
