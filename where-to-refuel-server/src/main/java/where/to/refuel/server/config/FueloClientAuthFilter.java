package where.to.refuel.server.config;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.filter.ClientFilterChain;
import io.micronaut.http.filter.HttpClientFilter;
import lombok.SneakyThrows;
import org.reactivestreams.Publisher;

import javax.inject.Inject;
import java.net.URI;

@Filter("/api/**")
@Requires(property = FueloConfig.PREFIX + ".apikey")
public class FueloClientAuthFilter implements HttpClientFilter {

  private final FueloConfig fueloConfig;

  @Inject
  public FueloClientAuthFilter(FueloConfig fueloConfig) {
    this.fueloConfig = fueloConfig;
  }

  @Override
  @SneakyThrows
  public Publisher<? extends HttpResponse<?>> doFilter(MutableHttpRequest<?> request, ClientFilterChain chain) {
    var originalUri = request.getUri();
    var authorizedQueryParams = String.format("%s&key=%s", originalUri.getQuery(), fueloConfig.getApikey());
    var authorizedUri = new URI(originalUri.getScheme(), originalUri.getUserInfo(), originalUri.getHost(),
      originalUri.getPort(), originalUri.getPath(), authorizedQueryParams, originalUri.getFragment());
    return chain.proceed(request.uri(authorizedUri));
  }
}
