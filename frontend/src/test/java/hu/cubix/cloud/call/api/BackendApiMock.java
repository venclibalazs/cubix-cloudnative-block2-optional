package hu.cubix.cloud.call.api;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

import static java.nio.charset.Charset.defaultCharset;
import static org.springframework.util.StreamUtils.copyToString;

public class BackendApiMock {

    public static WireMockServer createMockServer() {
        return new WireMockServer(18080);
    }

    public static void setupMockResponses(WireMockServer mockService, String source) throws IOException {
        mockService.stubFor(WireMock.get(WireMock.urlEqualTo("/backend"))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(
                                copyToString(
                                        BackendApiMock.class.getClassLoader().getResourceAsStream("response/mock-message.json"),
                                        defaultCharset())
                                        .replace("SOURCE_PLACEHOLDER", source)
                        )));
    }
}
