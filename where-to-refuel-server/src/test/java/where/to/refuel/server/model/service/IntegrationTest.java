package where.to.refuel.server.model.service;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeAll;

public class IntegrationTest {

  @Rule
  public static final WireMockRule wireMock = new WireMockRule(8090);

  @BeforeAll
  static void setup() {
    wireMock.start();
  }
}
