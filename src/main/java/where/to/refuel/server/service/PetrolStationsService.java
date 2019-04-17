package where.to.refuel.server.service;

import where.to.refuel.server.model.FuelType;
import where.to.refuel.server.model.Location;
import where.to.refuel.server.model.PetrolStation;

import java.util.List;

public interface PetrolStationsService {
  List<PetrolStation> findByLocationAndFuelType(Location location, FuelType fuelType);
}
