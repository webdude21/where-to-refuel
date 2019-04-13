package where.to.refuel.server.api;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.util.Map;

@Controller("/health")
public class HealthController {

    private final Map<String, Object> healthy = Map.of("status", "healthy");

    @Get(produces = MediaType.APPLICATION_JSON)
    public Map<String, Object> index() {
        return healthy;
    }
}
