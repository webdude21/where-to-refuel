package where.to.refuel.server.config;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.context.annotation.Requires;

@ConfigurationProperties(BingConfig.PREFIX)
@Requires(property = BingConfig.PREFIX)
@Requires(property = BingConfig.PREFIX + ".apikey")
@Requires(property = BingConfig.PREFIX + ".base")
@Requires(property = BingConfig.PREFIX + ".version")
public class BingConfig {
  static final String PREFIX = "bing";
  private String apikey;
  private String base;
  private String version;

  public String getBase() {
    return base;
  }

  public void setBase(String base) {
    this.base = base;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getApikey() {
    return apikey;
  }

  public void setApikey(String apikey) {
    this.apikey = apikey;
  }
}
