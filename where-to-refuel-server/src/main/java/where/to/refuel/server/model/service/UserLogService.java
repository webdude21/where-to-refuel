package where.to.refuel.server.model.service;

import where.to.refuel.server.model.Coordinates;
import where.to.refuel.server.model.FuelType;

public interface UserLogService {
  void logUserInfo(Coordinates location, FuelType fuelType, String ipAddress);
}
