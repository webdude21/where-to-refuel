package where.to.refuel.server.config;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.context.annotation.Requires;
import lombok.Data;

@ConfigurationProperties(FueloConfig.PREFIX)
@Data
@Requires(property = FueloConfig.PREFIX)
@Requires(property = FueloConfig.PREFIX + ".apikey")
class FueloConfig {
  static final String PREFIX = "fuelo";
  String apikey;
}
