package where.to.refuel.server.util;

import io.micronaut.http.HttpRequest;
import org.apache.commons.lang3.StringUtils;

public class HttpUtils {
  public static String extractIpAddress(final HttpRequest<?> httpRequest) {
    var forwardedFor = httpRequest.getHeaders().get("HTTP_X_FORWARDED_FOR");

    if (StringUtils.isBlank(forwardedFor)) {
      return httpRequest.getRemoteAddress().getAddress().getHostAddress();
    }

    var ipAddresses = StringUtils.splitPreserveAllTokens(forwardedFor, ",");

    return ipAddresses[ipAddresses.length - 1];
  }
}
