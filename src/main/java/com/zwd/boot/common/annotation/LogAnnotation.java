package com.zwd.boot.common.annotation;

import com.zwd.boot.common.enums.PlatformEnum;

import java.lang.annotation.*;

/**
 * 日志注解
 * @author 随风逐梦
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {
    /**
     * 方法描述
     */
    String value() default "";

    /**
     * 默认为后台管理
     */
    PlatformEnum platform() default PlatformEnum.ADMIN;

    /**
     * 是否保存到数据库
     */
    boolean save() default true;
}
