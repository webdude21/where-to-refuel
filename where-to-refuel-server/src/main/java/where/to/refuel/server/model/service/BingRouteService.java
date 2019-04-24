package where.to.refuel.server.model.service;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.uri.UriTemplate;
import io.reactivex.Flowable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import where.to.refuel.server.config.BingConfig;
import where.to.refuel.server.dto.DrivingInformationRequestTO;
import where.to.refuel.server.dto.DrivingInformationResponseTO;
import where.to.refuel.server.dto.DrivingInformationTO;
import where.to.refuel.server.model.Coordinates;
import where.to.refuel.server.model.Location;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class BingRouteService implements DrivingInformationService {

  private static final Logger log = LoggerFactory.getLogger(DrivingInformationService.class);
  private final RxHttpClient httpClient;
  private final BingConfig bingConfig;

  @Inject
  BingRouteService(@Client("bing") RxHttpClient httpClient, BingConfig bingConfig) {
    this.httpClient = httpClient;
    this.bingConfig = bingConfig;
  }

  @Override
  public <T extends Location> Flowable<T> findDrivingInformationFor(Coordinates origin, List<T> destinations) {
    if (destinations.size() == 0) {
      log.info("Calling the service without destinations, origin is: {}", origin);
      return Flowable.fromIterable(Collections.emptyList());
    }

    var destinationCoords = destinations.stream().map(Location::getCoordinates).collect(Collectors.toList());
    var requestTO = DrivingInformationRequestTO.of(Collections.singletonList(origin), destinationCoords);
    var uriTemplate = UriTemplate.of("/{base}/{version}/Routes/DistanceMatrix?key={apikey}");
    var response = httpClient.retrieve(HttpRequest.POST(uriTemplate.expand(bingConfig), requestTO), DrivingInformationResponseTO.class);

    return response.map(DrivingInformationResponseTO::getDrivingInformationTOs)
      .flatMap(Flowable::fromIterable)
      .map(di -> addDrivingInfo(destinations, di));
  }

  private <T extends Location> T addDrivingInfo(List<T> destinations, DrivingInformationTO di) {
    var location = destinations.get(di.getDestinationIndex());
    location.setDrivingInfo(di.toDrivingInfo());
    return location;
  }
}
