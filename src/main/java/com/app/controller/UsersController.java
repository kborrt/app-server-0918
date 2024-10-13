package com.app.controller;


import com.app.common.Result;
import com.app.entity.Users;
import com.app.entity.request.LoginDto;
import com.app.entity.response.PersonalVo;
import com.app.exception.CommonException;
import com.app.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.app.common.BaseController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Byterain
 * @since 2024-09-18
 */
@RestController
@RequestMapping("/users")
@Slf4j
public class UsersController extends BaseController {
    @Autowired
    private UsersService usersService;
    //用户登录，返回token
    @PostMapping("/login")
    public Result<String> login(@RequestBody LoginDto loginDto){
        return usersService.login(loginDto);
    }
    //用户注册
    @PostMapping("/register")
    public Result<Boolean> register(@RequestBody Users users){
        return usersService.register(users);
    }
    //根据用户id查询用户信息
    @GetMapping("/getUserDetail")
    public Result<PersonalVo> getUserDetail() {
        return usersService.getUserDetail();
    }


}
