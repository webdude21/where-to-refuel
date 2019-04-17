package where.to.refuel.server.dto;

import lombok.Value;
import where.to.refuel.server.model.FuelType;
import where.to.refuel.server.model.Location;

@Value
public class NearByPetrolStationsRequestTO {
  final double latitude;
  final double longitude;
  final String fuel;

  private NearByPetrolStationsRequestTO(Location location, FuelType fuelType) {
    latitude = location.getLatitude();
    longitude = location.getLongitude();
    fuel = fuelType.getKey();
  }

  public static NearByPetrolStationsRequestTO of(Location location, FuelType fuelType) {
    return new NearByPetrolStationsRequestTO(location, fuelType);
  }
}
