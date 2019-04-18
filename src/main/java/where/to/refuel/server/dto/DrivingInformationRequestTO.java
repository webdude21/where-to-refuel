package where.to.refuel.server.dto;

import lombok.Data;
import where.to.refuel.server.model.Coordinates;

import java.util.List;

@Data(staticConstructor = "of")
public class DrivingInformationRequestTO {
  private final String travelMode = "driving";
  private final List<Coordinates> origins;
  private final List<Coordinates> destinations;
}
