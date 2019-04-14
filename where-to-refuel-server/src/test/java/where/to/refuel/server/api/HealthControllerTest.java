package where.to.refuel.server.api;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@MicronautTest
class HealthControllerTest {

    @Inject
    @Client("/")
    RxHttpClient client;

    @Test
    void theHealthEndpointShouldWork() {
        String result = client.toBlocking().retrieve(HttpRequest.GET("/health"), String.class);
        Assertions.assertEquals("{\"status\":\"healthy\"}", result);
    }
}
