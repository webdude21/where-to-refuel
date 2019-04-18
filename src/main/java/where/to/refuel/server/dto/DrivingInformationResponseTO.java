package where.to.refuel.server.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class DrivingInformationResponseTO {

  private List<DrivingInformationTO> drivingInformationTOs;

  @JsonProperty("resourceSets")
  private void unpackNested(List<Object> objects) {
    var resources = ((LinkedHashMap) objects.get(0)).get("resources");
    ArrayList<LinkedHashMap<String, ? extends Number>> results = (ArrayList<LinkedHashMap<String, ? extends Number>>) ((LinkedHashMap) ((ArrayList) resources).get(0)).get("results");
    drivingInformationTOs = results.stream().map(DrivingInformationTO::from).collect(Collectors.toList());
  }
}
