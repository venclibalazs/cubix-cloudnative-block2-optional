package hu.cubix.cloud.api.controller;

import com.github.tomakehurst.wiremock.WireMockServer;
import hu.cubix.cloud.call.api.BackendApiMock;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest(properties = {"frontend.default.message=changed"})
@AutoConfigureMockMvc
class FrontendControllerCustomDefaultIT {

    private static final String DEFAULT_MESSAGE = "changed";
    private static final String SOURCE = "sourceValue";
    private static WireMockServer messageApi;
    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    static void setUp() throws IOException {
        messageApi = BackendApiMock.createMockServer();
        BackendApiMock.setupMockResponses(messageApi, SOURCE);
        messageApi.start();
    }
    @AfterAll
    static void afterAll() {
        messageApi.stop();
    }

    @Test
    void call() throws Exception {
        mockMvc.perform(get("/frontend"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(FrontendMatchers.createMsForReplyJsonPathMatcherForExample())
                .andExpect(FrontendMatchers.createFrontendMessageJsonPathMatcher(DEFAULT_MESSAGE))
                .andExpect(FrontendMatchers.createBackendSourceDataHomeworkOwnerJsonPathMatcher(SOURCE));
    }
}