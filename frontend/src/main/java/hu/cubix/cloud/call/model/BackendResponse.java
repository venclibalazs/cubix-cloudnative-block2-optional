package hu.cubix.cloud.call.model;

import java.time.LocalDateTime;

public record BackendResponse(LocalDateTime time, String source) {
}
