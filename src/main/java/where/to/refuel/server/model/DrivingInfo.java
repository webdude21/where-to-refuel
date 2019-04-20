package where.to.refuel.server.model;

import lombok.Value;

@Value(staticConstructor = "of")
public class DrivingInfo {
  double distance;
  double duration;
}
