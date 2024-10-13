package com.app.entity.request;

import lombok.Data;
//该实体类专门用于接收前端传来的登录请求参数
@Data
public class LoginDto {
    private String userId;
    private String password;
}
