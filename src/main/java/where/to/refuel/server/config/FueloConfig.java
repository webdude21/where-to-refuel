package where.to.refuel.server.config;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.context.annotation.Requires;
import lombok.Data;
import lombok.Getter;

@ConfigurationProperties(FueloConfig.PREFIX)
@Data
@Requires(property = FueloConfig.PREFIX)
@Requires(property = FueloConfig.PREFIX + ".apikey")
public class FueloConfig {
  static final String PREFIX = "fuelo";
  private String apikey;
}
