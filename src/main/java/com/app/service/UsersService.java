package com.app.service;

import com.app.common.Result;
import com.app.entity.Users;
import com.app.entity.request.LoginDto;
import com.app.entity.response.PersonalVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Byterain
 * @since 2024-09-18
 */
public interface UsersService extends IService<Users> {


    //用户登录，登陆成功返回token令牌
    public Result<String> login(LoginDto loginDto);

    //用户注册
    public Result<Boolean> register(Users users);

    //根据用户id查询用户信息
    public Result<PersonalVo> getUserDetail();

}
