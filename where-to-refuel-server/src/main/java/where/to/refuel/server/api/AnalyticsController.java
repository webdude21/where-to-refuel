package where.to.refuel.server.api;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.reactivex.Flowable;
import where.to.refuel.server.dto.UserLogTO;
import where.to.refuel.server.model.service.UserLogService;

import javax.inject.Inject;

@Controller("/analytics")
public class AnalyticsController {

  private final UserLogService userLogService;

  @Inject
  public AnalyticsController(UserLogService userLogService) {
    this.userLogService = userLogService;
  }

  @Get(value = "/user-logs", produces = MediaType.APPLICATION_JSON)
  public Flowable<UserLogTO> findAllUserLogs() {
    return userLogService.retrieveSearchLogs().map(UserLogTO::from);
  }
}
