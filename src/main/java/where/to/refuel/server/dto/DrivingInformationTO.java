package where.to.refuel.server.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import where.to.refuel.server.model.DrivingInfo;

import java.util.LinkedHashMap;

@Data
@NoArgsConstructor
public class DrivingInformationTO {
  private int destinationIndex;
  private int originIndex;
  private double travelDistance;
  private double travelDuration;

  static DrivingInformationTO from(LinkedHashMap<String, ? extends Number> linkedHashMap) {
    var drivingInformationTO = new DrivingInformationTO();
    drivingInformationTO.setDestinationIndex(linkedHashMap.get("destinationIndex").intValue());
    drivingInformationTO.setOriginIndex(linkedHashMap.get("originIndex").intValue());
    drivingInformationTO.setTravelDistance(linkedHashMap.get("travelDistance").doubleValue());
    drivingInformationTO.setTravelDuration(linkedHashMap.get("travelDuration").doubleValue());

    return drivingInformationTO;
  }

  public DrivingInfo toDrivingInfo() {
    return DrivingInfo.of(travelDistance, travelDuration);
  }
}
