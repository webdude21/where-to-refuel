package where.to.refuel.server.model;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum FuelType {
  PETROL("gasoline"), DIESEL("diesel"), LPG("lpg"), CNG("methane");

  private String key;

  FuelType(String key) {
    this.key = key;
  }

  FuelType fromKey(String key) {
    return Arrays.stream(FuelType.values())
      .filter(fuelType -> fuelType.getKey().equals(key))
      .findAny()
      .orElseThrow();
  }
}
