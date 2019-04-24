package where.to.refuel.server.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Optional;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class PetrolStation extends Location {
  private final Brand brand;
  private PriceInformation priceInformation;

  public PetrolStation(int id, String name, String city, String address, Coordinates coordinates,
                       double absoluteDistance, Brand brand) {
    super(id, name, city, address, coordinates, absoluteDistance);
    this.brand = brand;
  }

  public static boolean hasValidDistance(PetrolStation petrolStation) {
    return Optional.ofNullable(petrolStation.getDrivingInfo()).stream()
      .anyMatch(drivingInfo -> petrolStation.getAbsoluteDistance() < drivingInfo.getDistance());
  }
}
