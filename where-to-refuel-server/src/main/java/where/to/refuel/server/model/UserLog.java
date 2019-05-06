package where.to.refuel.server.model;

import lombok.Value;

import java.time.LocalDateTime;

@Value(staticConstructor = "of")
public class UserLog {
  Coordinates location;
  String fuelType;
  String ipAddress;
  LocalDateTime requestedOn;
}
