package where.to.refuel.server.model.service;

import where.to.refuel.server.model.Coordinates;
import where.to.refuel.server.model.FuelType;
import where.to.refuel.server.model.PetrolStation;

import java.util.List;

public interface PetrolStationsService {
  List<PetrolStation> findByLocationAndFuelType(Coordinates coordinates, FuelType fuelType);
}
