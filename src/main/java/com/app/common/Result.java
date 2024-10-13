package com.app.common;

import cn.hutool.http.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//统一返回结果的类
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    //操作成功 字符描述常量
    public static final String REQUEST_OK="OK";
    private int code; //返回状态码
    private String msg; //返回操作的描述
    private T data; //返回数据
    //请求成功 一些请求操作成功，但是没有返回数据，第三参数设置null。
    public static Result<Void> ok(){
        return new Result<Void>(HttpStatus.HTTP_OK,REQUEST_OK,null);
    }
    //请求成功 还有一些请求，是查询数据，请求成功是要返回data数据。
    public static <T> Result<T> ok(T data){
        return new Result<T>(HttpStatus.HTTP_OK,REQUEST_OK,data);
    }
    //请求失败 默认请求失败状态码400
    public static <T> Result<T> error(String msg){
        return new Result<>(HttpStatus.HTTP_BAD_REQUEST,msg,null);
    }
//请求失败 手动设置失败的状态码
    public static <T> Result<T> error(int code,String msg){
        return new Result<>(code,msg,null);
    }

}
