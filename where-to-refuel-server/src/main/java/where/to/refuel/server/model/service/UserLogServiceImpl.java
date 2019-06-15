package where.to.refuel.server.model.service;

import io.reactivex.Flowable;
import where.to.refuel.server.model.Coordinates;
import where.to.refuel.server.model.FuelType;
import where.to.refuel.server.model.UserLog;
import where.to.refuel.server.model.repository.UserLogRepository;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.LocalDateTime;

@Singleton
public class UserLogServiceImpl implements UserLogService {

  private final UserLogRepository userLogRepository;

  @Inject
  public UserLogServiceImpl(UserLogRepository userLogRepository) {
    this.userLogRepository = userLogRepository;
  }

  @Override
  public void logUserInfo(Coordinates location, FuelType fuelType, String ipAddress) {
    var userLog = UserLog.of(location, fuelType.getKey(), ipAddress, LocalDateTime.now());
    userLogRepository.logUserInfo(userLog).subscribe();
  }

  @Override
  public Flowable<UserLog> retrieveSearchLogs() {
    return userLogRepository.findAllUserLogs();
  }
}
