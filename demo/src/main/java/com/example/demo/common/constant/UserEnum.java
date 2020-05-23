package com.example.demo.common.constant;

public enum UserEnum implements ResultCode {
    LOGIN_SUCCESS(10000,"登录成功"),
    LOGIN_FAIL(10001,"登录失败")
    ;

    private Integer code;

    private String message;

    UserEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
