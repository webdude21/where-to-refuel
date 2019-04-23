package where.to.refuel.server.model.service;

import io.reactivex.Single;
import where.to.refuel.server.model.Coordinates;
import where.to.refuel.server.model.FuelType;

import java.util.Map;

public interface PetrolStationPriceService {
  Single<Map<Integer, Double>> findByLocationAndFuelType(Coordinates coordinates, FuelType fuelType);
}
