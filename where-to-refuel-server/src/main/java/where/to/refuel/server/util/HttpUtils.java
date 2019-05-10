package where.to.refuel.server.util;

import io.micronaut.http.HttpRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUtils {
  private static final Logger log = LoggerFactory.getLogger(HttpUtils.class);

  public static String extractIpAddress(final HttpRequest<?> httpRequest) {
    var forwardedFor = httpRequest.getHeaders().get("HTTP_X_FORWARDED_FOR");

    if (StringUtils.isBlank(forwardedFor)) {
      log.info("Got the ip {} from the request.", forwardedFor);
      return httpRequest.getRemoteAddress().getAddress().getHostAddress();
    }

    var ipAddresses = StringUtils.splitPreserveAllTokens(forwardedFor, ",");
    var lastIpAddress = ipAddresses[ipAddresses.length - 1];

    log.info("Got the ip {} from the headers, full string is {}", lastIpAddress, ipAddresses);

    return lastIpAddress;
  }
}
