package where.to.refuel.server.model;

import lombok.Value;

@Value(staticConstructor = "of")
public class Location {
  double latitude;
  double longitude;
}
