package com.martinps.response;

import java.time.LocalDateTime;

public class ErrorResponse {
    private boolean success;
    private String message;
    private LocalDateTime timestamp;

    public ErrorResponse(boolean b, String msg, LocalDateTime now) {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}