package com.app.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.app.common.Result;
import com.app.entity.Users;
import com.app.entity.request.LoginDto;
import com.app.entity.response.PersonalVo;
import com.app.exception.CommonException;
import com.app.mapper.UsersMapper;
import com.app.service.UsersService;
import com.app.utils.JwtUtils;
import com.app.utils.MD5Utils;
import com.app.utils.UserContext;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {
    @Autowired
    private UsersMapper usersMapper;

    //MyBatis-plus 针对mapper层已经实现CRUD。

    @Override
    public Result<String> login(LoginDto loginDto) {
        Users users = usersMapper.selectById(loginDto.getUserId());
        if (ObjectUtil.isNull(users)) {
            throw new CommonException("用户不存在");
        }else{
            //如果按照输入手机号查询该用户存在，比较密码
            //将登录密码调用MD5Utils工具类先加工，再加密
            String pass = MD5Utils.md5(MD5Utils.inputPassToNewPass(loginDto.getPassword()));
            if(users.getPassword().equals(pass)){
                //生成token令牌
                JwtUtils jwtUtils = new JwtUtils();
                String token = jwtUtils.createToken(users.getUserId(),users.getRealName(),users.getUserType());
                return Result.ok(token);
            }else{
                throw new CommonException("密码错误");
            }

        }

    }

    @Override
    public Result<Boolean> register(Users users) {
        users.setUserType(1);
        String pass = MD5Utils.md5(MD5Utils.inputPassToNewPass(users.getPassword()));
        users.setPassword(pass);
        try{
            int i=usersMapper.insert(users);
            if(i<=0){
                throw new CommonException("注册失败");
            }
            return Result.ok(true);
        }catch (DuplicateKeyException e){
            throw new CommonException("手机号已存在");
        }catch (Exception e){
            throw new CommonException("注册失败"+e.getMessage());
        }


    }

    @Override
    public Result<PersonalVo> getUserDetail() {
        String userId = UserContext.currentUserId();
        Users users = usersMapper.selectById(userId);
        PersonalVo personalVo = new PersonalVo();

        personalVo.setUserId(users.getUserId());
        personalVo.setRealName(users.getRealName());
        personalVo.setSex(users.getSex());
        personalVo.setIdentityCard(users.getIdentityCard());
        personalVo.setBirthday(users.getBirthday());
        personalVo.setUserType(users.getUserType());

        return Result.ok(personalVo);
    }
}
