package com.app.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.JWTValidator;
import com.app.entity.request.CurrentUserInfo;
import org.springframework.util.Assert;
import org.springframework.util.Base64Utils;
import java.util.Date;
import java.nio.charset.StandardCharsets;

public class JwtUtils {
    //负载部分存储key
    private static final String PAYLOAD_KEY = "user";
    private long expire = 604800; //过期时间 单位是秒
    private String secret = "byterain"; //设置秘钥
    private byte[] key;

    public JwtUtils() {
        //将上面定义秘钥 转换为 字节数组
        key = secret.getBytes(StandardCharsets.UTF_8);
    }

    //创建 jwt token
    //token令牌 payload负载部分，可以自定义字段，将登录用户基本信息存储到token。
    //userId,name,userType参数
    public String createToken(String userId, String name, int userType) {
        //将name名称 使用base64编码，防止token无法解析
        String encodeName = StrUtil.isEmpty(name)?null:Base64.encode(name, StandardCharsets.UTF_8);
        //当前日期
        Date nowDate = new Date();
        //设置过期的日期 nowDate.getTime() 返回当前时间毫秒数 1秒=1000毫秒
        Date expireDate = new Date(nowDate.getTime() + expire * 1000);
        return JWT.create().setPayload(PAYLOAD_KEY, new
                        CurrentUserInfo(userId, name, userType))
                .setExpiresAt(expireDate)
                .setKey(key)
                .sign();
    }

    //解析token
    public CurrentUserInfo parseToken(String token)throws Exception {

            //解析token
            JWT jwt = JWTUtil.parseToken(token);
            //验证token是否过期
            JWTValidator.of(jwt).validateDate();
            //验证签名
            Assert.isTrue(jwt.setKey(key).verify(),"Invalid JWT Token");
            //获取payload部分
            JSONObject payload = jwt.getPayload().getClaimsJson();

            JSONObject userpayload = payload.getJSONObject(PAYLOAD_KEY);
            //获取用户信息
            String userId = userpayload.getStr("userId");
            String name = userpayload.getStr("name");
            int userType = userpayload.getInt("userType");
            //将name名称 使用base64解码
            if(StrUtil.isEmpty(name)){
                name=Base64.decodeStr(name,StandardCharsets.UTF_8);
            }
            return new CurrentUserInfo(userId, name, userType);

    }


}
