package com.pl.common.result;

/**
 * Created by brander on 2019/1/12
 */
public enum ResultStatus {
    OK(200, "SUCCESS"),

    ERROR(500, "ERROR"),

    NO_AUTH(500, "NO AUTH");
    private Integer code;

    private String message;

    ResultStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
