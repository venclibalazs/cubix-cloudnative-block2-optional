package hu.cubix.cloud.api.controller;

import hu.cubix.cloud.call.api.BackendApi;
import hu.cubix.cloud.call.model.BackendResponse;
import hu.cubix.cloud.model.FrontendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/frontend")
public class FrontendController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FrontendController.class);
    private final BackendApi api;
    private final String defaultMessage;

    public FrontendController(BackendApi api,
                              @Value("${frontend.default.message}") String defaultMessage) {
        this.api = api;
        this.defaultMessage = defaultMessage;
    }

    @GetMapping("/local")
    public FrontendResponse noCall(@RequestParam(required = false, name = "message") String message) {
        LOGGER.info("Local endpoint was called - no call will be made to backend - message was: {}", message);
        if (!StringUtils.hasText(message)) {
            message = defaultMessage;
        }
        return new FrontendResponse(0L, message, "<No call was made to the backend>");
    }

    @GetMapping
    public FrontendResponse call(@RequestParam(required = false, name = "message") String message) {
        LOGGER.info("Preparing for calling backend - message was: {}", message);
        if (!StringUtils.hasText(message)) {
            message = defaultMessage;
        }
        LocalDateTime start = LocalDateTime.now();
        LOGGER.info("Calling backend");
        BackendResponse backendResponse = api.backend();
        LOGGER.info("Backend call was successful, response was: {}", backendResponse);
        Duration timeBetween = Duration.between(start, backendResponse.time());

        FrontendResponse frontendResponse = new FrontendResponse(timeBetween.abs().toMillis(), message, backendResponse.source());
        LOGGER.info("Response will be: {}", frontendResponse);
        return frontendResponse;
    }
}
