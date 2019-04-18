package where.to.refuel.server.service;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.uri.UriTemplate;
import where.to.refuel.server.config.BingConfig;
import where.to.refuel.server.dto.DrivingInformationRequestTO;
import where.to.refuel.server.dto.DrivingInformationResponseTO;
import where.to.refuel.server.dto.DrivingInformationTO;
import where.to.refuel.server.model.Coordinates;
import where.to.refuel.server.model.Location;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Singleton
public class BingRouteService implements DrivingInformationService {

  private final RxHttpClient httpClient;
  private final BingConfig bingConfig;

  @Inject
  BingRouteService(@Client("bing") RxHttpClient httpClient, BingConfig bingConfig) {
    this.httpClient = httpClient;
    this.bingConfig = bingConfig;
  }

  @Override
  public <T extends Location> List<T> findDrivingInformationFor(Coordinates origin, List<T> destinations) {
    var destinationCoords = destinations.stream().map(Location::getCoordinates).collect(Collectors.toList());
    var requestTO = DrivingInformationRequestTO.of(Collections.singletonList(origin), destinationCoords);
    var uriTemplate = UriTemplate.of("/{base}/{version}/Routes/DistanceMatrix?key={apikey}");
    var response = httpClient.exchange(HttpRequest.POST(uriTemplate.expand(bingConfig), requestTO), DrivingInformationResponseTO.class);
    var body = response.blockingFirst().body();
    return Optional.ofNullable(body)
      .map(DrivingInformationResponseTO::getDrivingInformationTOs)
      .stream()
      .flatMap(Collection::stream)
      .map(di -> enrichLocation(destinations, di))
      .collect(Collectors.toList());
  }

  private <T extends Location> T enrichLocation(List<T> destinations, DrivingInformationTO di) {
    var location = destinations.get(di.getDestinationIndex());
    location.setDrivingInfo(di.toDrivingInfo());
    return location;
  }


}
