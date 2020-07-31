package com.zwd.boot.common.constant;

/**
 * @author 随风逐梦
 */
public class CookieConst {
    /**
     *  cookie的有效期默认为30天
     */
    public static final int COOKIE_MAX_AGE = 60 * 60 * 24 * 30;

    /**
     *  cookie加密时的额外的salt
     */
    public static final String salt = "123456789";

    /**
     * 自动登录的Cookie名
     */
    public static final String RememberMe = "remember-me";
}
