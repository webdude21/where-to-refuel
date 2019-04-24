package where.to.refuel.server.model.service;

import io.reactivex.Flowable;
import where.to.refuel.server.model.Coordinates;
import where.to.refuel.server.model.Location;

import java.util.List;

public interface DrivingInformationService {
  <T extends Location> Flowable<T> findDrivingInformationFor(Coordinates origin, List<T> destinations);
}
