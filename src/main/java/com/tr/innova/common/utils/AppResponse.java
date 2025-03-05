package com.tr.innova.common.utils;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AppResponse<T> {
    private final Integer status;
    private T data;
    private String errorMessage;

    public static AppResponse<Object> nullResponse() {
        return new AppResponse<>(null);
    }

    public static AppResponse<Object> ok(Object data) {
        return new AppResponse<>(data);
    }

    public AppResponse(T data) {
        this.status = HttpStatus.OK.value();
        this.data = data;
    }

    public AppResponse(Integer status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }

}
