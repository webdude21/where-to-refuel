package where.to.refuel.server.model;

import lombok.Value;

@Value(staticConstructor = "of")
public class PetrolStation {
  int id;
  Brand brand;
  Location location;
  String name;
  String city;
  String address;
  double distance;
}
