package hu.cubix.cloud.model;

public record FrontendResponse(long msForReply, String frontendMessage, String backendSourceData) {
}
