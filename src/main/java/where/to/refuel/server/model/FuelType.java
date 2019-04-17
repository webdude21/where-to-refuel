package where.to.refuel.server.model;

import lombok.Getter;

@Getter
public enum FuelType {
  PETROL("gasoline"), DIESEL("diesel"), LPG("lpg"), CNG("methane");

  private String key;

  FuelType(String key) {
    this.key = key;
  }
}
