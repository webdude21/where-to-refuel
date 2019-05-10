package where.to.refuel.server.util;

import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.net.InetAddress;
import java.net.InetSocketAddress;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class HttpUtilsTest {

  private static final String HTTP_X_FORWARDED_FOR = "REMOTE_ADDR";

  @Mock
  HttpRequest httpRequest;

  @Mock
  HttpHeaders httpHeaders;

  @Mock
  InetSocketAddress inetSocketAddress;

  @Mock
  InetAddress inetAddress;

  @BeforeEach
  void setUp() {
    initMocks(this);
  }

  @Test
  void extractIpAddressWhenHttpXForwardedForIsPresentShouldReturnTheLastIpAddress() {
    when(httpHeaders.get(HTTP_X_FORWARDED_FOR)).thenReturn("127.0.0.1,215.21.24.1,192.168.0.1");
    when(httpRequest.getHeaders()).thenReturn(httpHeaders);

    var expected = "192.168.0.1";
    var actual = HttpUtils.extractIpAddress(httpRequest);

    verify(httpHeaders, times(1)).get(HTTP_X_FORWARDED_FOR);
    verify(httpRequest, times(1)).getHeaders();

    verifyNoMoreInteractions(httpHeaders, httpRequest, inetSocketAddress, inetAddress);

    assertThat(expected, equalToIgnoringCase(actual));
  }

  @Test
  void extractIpAddressWhenHttpXForwardedForIsNotPresentShouldBeTheHttpIpAddress() {
    var expected = "192.168.0.1";
    when(httpHeaders.get(HTTP_X_FORWARDED_FOR)).thenReturn(null);
    when(httpRequest.getHeaders()).thenReturn(httpHeaders);
    when(httpRequest.getRemoteAddress()).thenReturn(inetSocketAddress);
    when(inetSocketAddress.getAddress()).thenReturn(inetAddress);
    when(inetAddress.getHostAddress()).thenReturn(expected);

    var actual = HttpUtils.extractIpAddress(httpRequest);

    verify(inetAddress, times(1)).getHostAddress();
    verify(inetSocketAddress, times(1)).getAddress();
    verify(httpHeaders, times(1)).get(HTTP_X_FORWARDED_FOR);
    verify(httpRequest, times(1)).getHeaders();
    verify(httpRequest, times(1)).getRemoteAddress();

    verifyNoMoreInteractions(httpHeaders, httpRequest, inetSocketAddress, inetAddress);

    assertThat(expected, equalToIgnoringCase(actual));
  }
}
