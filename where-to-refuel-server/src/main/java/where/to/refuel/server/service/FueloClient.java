package where.to.refuel.server.service;

import where.to.refuel.server.model.FuelType;
import where.to.refuel.server.model.Location;
import where.to.refuel.server.model.PetrolStations;

import javax.inject.Singleton;
import java.util.Collections;
import java.util.List;

@Singleton
public class FueloClient implements FuelPriceService {

  @Override
  public List<PetrolStations> findByLocationAndFuelType(Location location, FuelType fuelType) {
    validateLocation(location);
    return Collections.emptyList();
  }

  private void validateLocation(Location location) {
    if (location.getLatitude() < 0) {
      throw new IllegalArgumentException("Latitude should be a positive number.");
    }

    if (location.getLongitude() < 0) {
      throw new IllegalArgumentException("Longitude should be a positive number.");
    }
  }
}
