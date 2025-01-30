package com.example.demo.entity;

public class Response<T> {
    private String status; // "success" or "error"
    private String message; // Message describing the outcome
    private T response; // Data or null

    // Constructor
    public Response(String status, String message, T response) {
        this.status = status;
        this.message = message;
        this.response = response;
    }

    // Getters and setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }
}
