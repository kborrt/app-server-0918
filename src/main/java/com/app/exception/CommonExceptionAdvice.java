package com.app.exception;

import com.app.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
//项目代码发送异常，该类用于处理异常，执行相应的操作
@RestControllerAdvice
@Slf4j
public class CommonExceptionAdvice {
    //处理自定义异常
    //当项目中代码 发送CommonException异常，就会被改类的该方法捕获到，执行该方法。
    @ExceptionHandler(CommonException.class)
    public Result customException(CommonException e) {
        log.error("请求异常，message: {}", e.getMessage());
        return Result.error(e.getCode(),e.getMessage());
    }
    //处理系统异常
    @ExceptionHandler({Exception.class})
    public Result noCustomException(Exception e) {
        log.error("请求异常，", e);
        return Result.error(e.getMessage());
    }

}

