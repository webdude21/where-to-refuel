package where.to.refuel.server.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class PetrolStationsResponseTO {
  @JsonProperty(value = "gasstations")
  List<PetrolStationTO> petrolStationTOS = new ArrayList<>();
}
