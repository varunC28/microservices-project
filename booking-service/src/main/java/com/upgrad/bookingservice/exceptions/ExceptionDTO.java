package com.upgrad.bookingservice.exceptions;

public class ExceptionDTO {
    String message;
    String statusCode;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public ExceptionDTO(String message, String statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        return "ExceptionDTO{" +
                "message='" + message + '\'' +
                ", statusCode='" + statusCode + '\'' +
                '}';
    }
}
