package hu.cubix.cloud.api.controller;

import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.*;

class FrontendMatchers {
    private FrontendMatchers() {
    }

    static ResultMatcher createJsonPathMatcherForRoot(String fieldName, Object value) {
        return MockMvcResultMatchers.jsonPath("$." + fieldName, is(value));
    }

    static ResultMatcher createMsForReplyJsonPathMatcherForLocal() {
        return MockMvcResultMatchers.jsonPath("$.msForReply", is(0));
    }

    static ResultMatcher createMsForReplyJsonPathMatcherForExample() {
        return MockMvcResultMatchers.jsonPath("$.msForReply", greaterThan(365L * 24 * 60 * 60 * 1000), Long.class);
    }

    static ResultMatcher createFrontendMessageJsonPathMatcher(String message) {
        return createJsonPathMatcherForRoot("frontendMessage", message);
    }

    static ResultMatcher createBackendSourceDataHomeworkOwnerJsonPathMatcher(String source) {
        return createJsonPathMatcherForRoot("backendSourceData", source);
    }

}
