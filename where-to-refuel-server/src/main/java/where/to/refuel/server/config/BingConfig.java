package where.to.refuel.server.config;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.context.annotation.Requires;
import lombok.Data;

@Data
@ConfigurationProperties(BingConfig.PREFIX)
@Requires(property = BingConfig.PREFIX)
@Requires(property = BingConfig.PREFIX + ".apikey")
@Requires(property = BingConfig.PREFIX + ".base")
@Requires(property = BingConfig.PREFIX + ".version")
public class BingConfig {
  static final String PREFIX = "bing";
  String apikey;
  String base;
  String version;
}
