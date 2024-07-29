package com.example.demo.DTO;

public class Result {
    private String message;
    private boolean success;

    public Result(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public static Result success(String message) {
        return new Result(message, true);
    }

    public static Result success() {
        return new Result(null, true);
    }

    public static Result fail(String message) {
        return new Result(message, false);
    }

    public static Result fail() {
        return new Result(null, false);
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }
}
