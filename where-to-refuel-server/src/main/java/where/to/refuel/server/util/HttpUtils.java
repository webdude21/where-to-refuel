package where.to.refuel.server.util;

import io.micronaut.http.HttpRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUtils {
  private static final Logger log = LoggerFactory.getLogger(HttpUtils.class);

  public static String extractIpAddress(final HttpRequest<?> httpRequest) {
    var forwardedFor = httpRequest.getHeaders().get("x-forwarded-for");

    if (StringUtils.isBlank(forwardedFor)) {
      var hostAddress = httpRequest.getRemoteAddress().getAddress().getHostAddress();
      log.info("Got the ip {} from the request.", hostAddress);
      return hostAddress;
    }

    var ipAddresses = StringUtils.splitPreserveAllTokens(forwardedFor, ",");
    var lastIpAddress = ipAddresses[ipAddresses.length - 1];

    log.info("Got the ip {} from the headers, full string is {}", lastIpAddress, ipAddresses);

    return lastIpAddress;
  }
}
