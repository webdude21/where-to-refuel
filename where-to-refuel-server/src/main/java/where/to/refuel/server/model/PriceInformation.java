package where.to.refuel.server.model;

import lombok.Value;

@Value(staticConstructor = "of")
public class PriceInformation {
  FuelType fuelType;
  double price;
}
