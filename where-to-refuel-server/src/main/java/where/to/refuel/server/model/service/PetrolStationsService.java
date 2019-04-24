package where.to.refuel.server.model.service;

import io.reactivex.Flowable;
import where.to.refuel.server.model.Coordinates;
import where.to.refuel.server.model.FuelType;
import where.to.refuel.server.model.PetrolStation;

public interface PetrolStationsService {
  Flowable<PetrolStation> findByLocationAndFuelType(Coordinates coordinates, FuelType fuelType);
}
