package com.example.demo.DTO;

import lombok.Data;

@Data
public class Result {
    private String message;
    private boolean success;
    private Object data;

    public Result(String message, boolean success, Object data) {
        this.message = message;
        this.success = success;
        this.data = data;
    }

    public static Result success(Object data) {
        return new Result("success", true, data);
    }

    public static Result success() {
        return new Result("success", true, null);
    }

    public static Result fail(Object data) {
        return new Result("fail", false, data);
    }

    public static Result fail() {
        return new Result("fail", false, null);
    }
}
