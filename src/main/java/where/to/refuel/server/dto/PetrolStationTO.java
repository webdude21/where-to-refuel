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
  private int id;
  @JsonProperty("lat")
  private double latitude;
  @JsonProperty("lon")
  private double longitude;
  @JsonProperty("brand_name")
  private String brandName;
  @JsonProperty("brand_id")
  private int brandId;
  @JsonProperty("name")
  private String name;
  @JsonProperty("city")
  private String city;
  @JsonProperty("address")
  private String address;
  @JsonProperty("distance")
  private double distance;

  public PetrolStation toPetrolStation() {
    return new PetrolStation(id, name, city, address, Coordinates.of(latitude, longitude), distance, Brand.of(brandId, name));
  }
}
