package where.to.refuel.server.model.service;

import where.to.refuel.server.model.Coordinates;
import where.to.refuel.server.model.FuelType;

import java.util.Map;

public interface PetrolStationPriceService {
  Map<Integer, Double> findByLocationAndFuelType(Coordinates coordinates, FuelType fuelType);
}
