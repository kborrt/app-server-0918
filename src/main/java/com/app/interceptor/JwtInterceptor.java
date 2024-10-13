package com.app.interceptor;

import com.app.common.HeaderConstants;
import com.app.entity.request.CurrentUserInfo;
import com.app.exception.CommonException;
import com.app.utils.JwtUtils;
import com.app.utils.UserContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class JwtInterceptor implements HandlerInterceptor {
    //定义JwtUtils工具类对象
    private JwtUtils jwtUtils = new JwtUtils();
    //请求到达控制器之前执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse
            response, Object handler) throws Exception {
        //前端代码：修改axios请求，修改请求头，每次请求都会通过请求头 携带token。
        //请求头信息设置：token : token密文
        //从请求头中获得token密文
        String token = request.getHeader(HeaderConstants.REQUEST_TOKEN);
        if(token==null){
            throw new CommonException(401,"非法访问，请先登录！");
        }
        try{
            CurrentUserInfo currentUserInfo = jwtUtils.parseToken(token);
            if(currentUserInfo!=null){
                //从中获取到登录用户基本信息 (userId,name,userType)
                UserContext.set(currentUserInfo);
            }
            return true;
        }catch(Exception e){
            log.error("解析jwt token失败，原因：{}",e.getMessage());
            throw new CommonException(401,"身份验证失败，重新登录");
        }
    }
    //控制器已经响应完毕前端，资源的释放
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse
            response, Object handler, Exception ex) throws Exception {
    }
}
