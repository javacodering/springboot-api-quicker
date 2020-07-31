package com.zwd.boot.common.constant;

/**
 * 程序中公用的常量类
 * @author 随风逐梦
 * @create 2020-07-20 9:52
 */
public class CommonConst {
    /**
     * 安全密码(UUID生成)，作为盐值用于用户密码的加密
     */
    public static final String ZYD_SECURITY_KEY = "929123f8f17944e8b0a531045453e1f1";

    /**
     * 用户正常状态
     */
    public static final Integer USER_STATUS_NORMAL = 0;

    /**
     * 用户禁用状态
     */
    public static final Integer USER_STATUS_LOCK = -1;

    /**
     * 普通用户
     */
    public static final Integer USER_TYPE_NORMAL = 0;

    /**
     * 管理员
     */
    public static final Integer USER_TYPE_ADMIN = 1;
    /**
     * 正常状态
     */
    public static final Integer STATUS_NORMAL = 0;

    /**
     * 禁用状态
     */
    public static final Integer STATUS_DISABLE = -1;

    /**
     * 删除标志
     */
    public static final Integer DEL_FLAG = 1;


    /**
     * 程序默认的错误状态码
     */
    public static final int DEFAULT_ERROR_CODE = 500;

    /**
     * 程序默认的成功状态码
     */
    public static final int DEFAULT_SUCCESS_CODE = 200;

}
