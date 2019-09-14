package where.to.refuel.server.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import where.to.refuel.server.model.Brand;
import where.to.refuel.server.model.Coordinates;
import where.to.refuel.server.model.PetrolStation;

@Data
@NoArgsConstructor
public class PetrolStationTO {
  @JsonProperty("id")
  int id;
  @JsonProperty("lat")
  double latitude;
  @JsonProperty("lon")
  double longitude;
  @JsonProperty("brand_name")
  String brandName;
  @JsonProperty("brand_id")
  int brandId;
  @JsonProperty("name")
  String name;
  @JsonProperty("city")
  String city;
  @JsonProperty("address")
  String address;
  @JsonProperty("distance")
  double distance;

  public PetrolStation toPetrolStation() {
    return new PetrolStation(id, name, city, address, Coordinates.of(latitude, longitude), distance, Brand.of(brandId, brandName));
  }
}
