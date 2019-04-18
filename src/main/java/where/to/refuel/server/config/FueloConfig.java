package where.to.refuel.server.config;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.context.annotation.Requires;

@ConfigurationProperties(FueloConfig.PREFIX)
@Requires(property = FueloConfig.PREFIX)
@Requires(property = FueloConfig.PREFIX + ".apikey")
public class FueloConfig {
  static final String PREFIX = "fuelo";
  private String apikey;

  String getApikey() {
    return apikey;
  }

  public void setApikey(String apikey) {
    this.apikey = apikey;
  }
}
