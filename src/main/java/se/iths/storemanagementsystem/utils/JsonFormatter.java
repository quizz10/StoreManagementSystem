package se.iths.storemanagementsystem.utils;

public class JsonFormatter {

    private int statusCode;
    private String message;

    public JsonFormatter() {

    }

    public JsonFormatter(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
