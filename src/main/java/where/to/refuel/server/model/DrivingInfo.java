package where.to.refuel.server.model;

import lombok.Value;

@Value(staticConstructor = "of")
public class DrivingInfo {
  private final double distance;
  private final double duration;
}
