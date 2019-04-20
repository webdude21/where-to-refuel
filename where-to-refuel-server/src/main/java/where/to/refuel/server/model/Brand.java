package where.to.refuel.server.model;

import lombok.Value;

@Value(staticConstructor = "of")
public class Brand {
  long id;
  String name;
}
