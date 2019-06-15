package where.to.refuel.server.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import where.to.refuel.server.model.UserLog;

@Data
@NoArgsConstructor
public class UserLogTO {
  double latitude;
  double longitude;
  String fuelType;
  String ipAddress;
  String requestedOn;

  public static UserLogTO from(UserLog userLog) {
    var userLogTO = new UserLogTO();
    userLogTO.latitude = userLog.getLocation().getLatitude();
    userLogTO.longitude = userLog.getLocation().getLongitude();
    userLogTO.fuelType = userLog.getFuelType();
    userLogTO.requestedOn = userLog.getRequestedOn().toString();
    userLogTO.ipAddress = userLog.getIpAddress();
    return userLogTO;
  }
}
