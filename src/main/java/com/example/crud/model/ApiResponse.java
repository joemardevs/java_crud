package com.example.crud.model;

public class ApiResponse<T> {
    private final int statusCode;
    private final boolean status;
    private final String message;
    private final T data;

    public ApiResponse(int statusCode, boolean status, String message, T data) {
        this.statusCode = statusCode;
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

}
