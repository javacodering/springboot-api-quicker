package com.zwd.boot.utils;


import com.zwd.boot.common.constant.SessionConst;
import com.zwd.boot.holder.RequestHolder;
import com.zwd.boot.model.entity.SysUser;

import java.util.UUID;

/**
 * Session工具类
 * @author 随风逐梦
 */
public class SessionUtil {

    /**
     * 当前是否登录
     *
     * @return
     */
    public static boolean isLogin() {
        return null != SessionUtil.getUser();
    }

    /**
     * 获取session中的用户信息
     *
     * @return User
     */
    public static SysUser getUser() {
        return (SysUser) RequestHolder.getSession(SessionConst.USER_SESSION_KEY);
    }

    /**
     * 添加session
     *
     * @param user
     */
    public static void setUser(SysUser user) {
        RequestHolder.setSession(SessionConst.USER_SESSION_KEY, user);
    }

    /**
     * 删除session信息
     */
    public static void removeUser() {
        RequestHolder.removeSession(SessionConst.USER_SESSION_KEY);
    }

    /**
     * 获取session中的Token信息
     *
     * @return String
     */
    public static String getToken(String key) {
        return (String) RequestHolder.getSession(key);
    }

    /**
     * 添加Token
     */
    public static void setToken(String key) {
        RequestHolder.setSession(key, UUID.randomUUID().toString());
    }

    /**
     * 删除Token信息
     */
    public static void removeToken(String key) {
        RequestHolder.removeSession(key);
    }

    /**
     * 获取验证码
     */
    public static String getKaptcha() {
        return (String) RequestHolder.getSession(SessionConst.KAPTCHA_SESSION_KEY);
    }

    /**
     * 保存验证码
     */
    public static void setKaptcha(String kaptcha) {
        RequestHolder.setSession(SessionConst.KAPTCHA_SESSION_KEY, kaptcha);
    }

    /**
     * 保存验证码
     */
    public static void removeKaptcha() {
        RequestHolder.removeSession(SessionConst.KAPTCHA_SESSION_KEY);
    }

    /**
     * 删除所有的session信息
     */
    public static void removeAllSession() {
        String[] keys = RequestHolder.getSessionKeys();
        if (keys != null && keys.length > 0) {
            for (String key : keys) {
                RequestHolder.removeSession(key);
            }
        }
    }
}
