package com.app.utils;

import com.app.entity.request.CurrentUserInfo;

//记录登录用户的信息
public class UserContext {
    //使用线程存储用户信息
    private static final ThreadLocal<CurrentUserInfo> THREAD_LOCAL_USER = new ThreadLocal<>();

    //设置当前用户
    public static void set(CurrentUserInfo currentUserInfo) {
        THREAD_LOCAL_USER.set(currentUserInfo);
    }
    //获取当前线程中的用户
    public static CurrentUserInfo currentUser() {
        return THREAD_LOCAL_USER.get();
    }
    public static String currentUserId(){
        return THREAD_LOCAL_USER.get().getUserId();
    }
    //清除当前线程中的用户
    public static void clear() {
        THREAD_LOCAL_USER.remove();
    }
}
