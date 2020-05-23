package com.example.demo.common.constant;

import lombok.Data;

import java.io.Serializable;

/**
 * 响应结果包装类
 *
 * @param <T> 结果类型
 */
@Data
public class Result<T> implements Serializable {
    public Result() {
    }

    public Result(T result) {
        this(true, 20000, null, result);
    }

    public Result(ResultCode code) {
        this(false, code.getCode(), code.getMessage(), null);
    }

    public Result(ResultCode code, String message) {
        this(false, code.getCode(), message, null);
    }

    public Result(int code, String message) {
        this(false, code, message, null);
    }

    public Result(boolean success, int code, String message, T result) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.result = result;
    }

    private boolean success;

    private int code;

    private String message;

    private T result;
}
