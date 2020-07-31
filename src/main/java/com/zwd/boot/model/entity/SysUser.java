package com.zwd.boot.model.entity;

import com.zwd.boot.common.base.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * @author 随风逐梦
 * @create 2020-07-20 11:49
 */
@Data
public class SysUser extends BaseEntity {
    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String mobile;
    private String email;
    private String qq;
    private Date birthday;
    private Integer gender;
    private String avatar;
    //用户类型
    private String userType;
    //博客地址
    private String blog;
    //地址
    private String location;
    //是否公开个人信息
    private Integer privacy;
    private String regIp;
    private String lastLoginIp;
    private Date lastLoginTime;
    private Integer loginCount;
    private String remark;
}
