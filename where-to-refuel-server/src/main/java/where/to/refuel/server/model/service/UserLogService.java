package where.to.refuel.server.model.service;

import io.reactivex.Flowable;
import where.to.refuel.server.model.Coordinates;
import where.to.refuel.server.model.FuelType;
import where.to.refuel.server.model.UserLog;

public interface UserLogService {
  void logUserInfo(Coordinates location, FuelType fuelType, String ipAddress);

  Flowable<UserLog> retrieveSearchLogs();
}
