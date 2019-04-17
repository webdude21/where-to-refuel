package where.to.refuel.server.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import where.to.refuel.server.model.FuelType;
import where.to.refuel.server.model.Location;

@Data
@NoArgsConstructor
public class NearByPetrolStationsRequestTO {
  private double latitude;
  private double longitude;
  private String fuel;

  private NearByPetrolStationsRequestTO(Location location, FuelType fuelType) {
    latitude = location.getLatitude();
    longitude = location.getLongitude();
    fuel = fuelType.getKey();
  }

  public static NearByPetrolStationsRequestTO of(Location location, FuelType fuelType) {
    return new NearByPetrolStationsRequestTO(location, fuelType);
  }
}
