package where.to.refuel.server.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PetrolStation extends Location {
  private final Brand brand;

  public PetrolStation(int id, String name, String city, String address, Coordinates coordinates,
                       double absoluteDistance, Brand brand) {
    super(id, name, city, address, coordinates, absoluteDistance);
    this.brand = brand;
  }
}
