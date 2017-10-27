package ru.alexandertesbsnko.shoplist3.data_source.common;

import java.io.Serializable;

public class BasicInfoCode implements Serializable {
    private Integer code;
    private String message;

    public BasicInfoCode() {}

    public BasicInfoCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}