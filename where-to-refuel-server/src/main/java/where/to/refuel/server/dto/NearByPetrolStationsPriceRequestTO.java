package where.to.refuel.server.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import where.to.refuel.server.model.Coordinates;
import where.to.refuel.server.model.FuelType;

@Data
@NoArgsConstructor
public class NearByPetrolStationsPriceRequestTO {
  private final String lang = "bg";
  private double lat;
  private double lon;
  private String type;

  private NearByPetrolStationsPriceRequestTO(Coordinates coordinates, FuelType fuelType) {
    lat = coordinates.getLatitude();
    lon = coordinates.getLongitude();
    type = fuelType.getKey();
  }

  public static NearByPetrolStationsPriceRequestTO of(Coordinates coordinates, FuelType fuelType) {
    return new NearByPetrolStationsPriceRequestTO(coordinates, fuelType);
  }
}
