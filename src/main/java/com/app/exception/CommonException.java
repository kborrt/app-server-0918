package com.app.exception;

import cn.hutool.http.HttpStatus;
import lombok.Data;

@Data
public class CommonException extends RuntimeException{
    private int code;   //执行返回的状态码
    private String message;   //执行返回的错误信息

    public CommonException(int code, String message) {
        this.code = code;
        this.message = message;
    }
    public CommonException(String message) {
        this(HttpStatus.HTTP_INTERNAL_ERROR, message);
    }
}
