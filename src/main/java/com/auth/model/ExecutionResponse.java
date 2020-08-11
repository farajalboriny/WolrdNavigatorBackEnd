package com.auth.model;

public class ExecutionResponse {
    private String message;

    public ExecutionResponse() {
    }

    public ExecutionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}