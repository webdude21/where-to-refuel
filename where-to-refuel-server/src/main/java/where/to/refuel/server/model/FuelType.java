package where.to.refuel.server.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FuelType {
  PETROL("gasoline"), DIESEL("diesel"), LPG("lpg"), CNG("methane");
  private final String key;
}
