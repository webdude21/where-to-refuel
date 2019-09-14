package where.to.refuel.server.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import where.to.refuel.server.model.Coordinates;
import where.to.refuel.server.model.FuelType;

@Data
@NoArgsConstructor
public class NearByPetrolStationsRequestTO {
  double latitude;
  double longitude;
  String fuel;
  int limit = 50;

  private NearByPetrolStationsRequestTO(Coordinates coordinates, FuelType fuelType) {
    latitude = coordinates.getLatitude();
    longitude = coordinates.getLongitude();
    fuel = fuelType.getKey();
  }

  public static NearByPetrolStationsRequestTO of(Coordinates coordinates, FuelType fuelType) {
    return new NearByPetrolStationsRequestTO(coordinates, fuelType);
  }
}
