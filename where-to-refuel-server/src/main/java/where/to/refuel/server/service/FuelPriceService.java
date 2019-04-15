package where.to.refuel.server.service;

import where.to.refuel.server.model.FuelType;
import where.to.refuel.server.model.Location;
import where.to.refuel.server.model.PetrolStations;

import java.util.List;

public interface FuelPriceService {
  List<PetrolStations> findByLocationAndFuelType(Location location, FuelType fuelType);
}
