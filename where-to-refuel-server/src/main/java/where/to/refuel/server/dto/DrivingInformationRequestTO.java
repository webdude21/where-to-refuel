package where.to.refuel.server.dto;

import lombok.Data;
import where.to.refuel.server.model.Coordinates;

import java.util.List;

@Data(staticConstructor = "of")
public class DrivingInformationRequestTO {
  final String travelMode = "driving";
  final List<Coordinates> origins;
  final List<Coordinates> destinations;
}
