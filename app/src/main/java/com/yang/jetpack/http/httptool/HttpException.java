package com.yang.jetpack.http.httptool;

import androidx.annotation.Nullable;

/**
 * Created by yxm on 2021/1/20.
 */
public class HttpException extends RuntimeException {
    int code;
    String message;

    public HttpException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Nullable
    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
