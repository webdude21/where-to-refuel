package where.to.refuel.server.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class DrivingInformationResponseTO {

  List<DrivingInformationTO> drivingInformationTOs;

  @JsonProperty("resourceSets")
  @SuppressWarnings("unchecked")
  private void unpackResult(List<Map<String, List>> objects) {
    var resources = (Map) objects.get(0).get("resources").get(0);
    var results = (List<Map<String, ? extends Number>>) resources.get("results");
    drivingInformationTOs = results.stream().map(DrivingInformationTO::from).collect(Collectors.toList());
  }
}
