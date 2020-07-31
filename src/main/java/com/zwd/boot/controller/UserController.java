package com.zwd.boot.controller;


import com.github.pagehelper.PageInfo;
import com.zwd.boot.common.annotation.LogAnnotation;
import com.zwd.boot.common.result.ResponseVO;
import com.zwd.boot.model.vo.UserConditionVO;
import com.zwd.boot.model.entity.SysUser;
import com.zwd.boot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 后台用户管理控制器
 * 异常不用捕获，用统一异常捕获处理
 */
@Slf4j
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 获得用户列表
     */
    @GetMapping("/list")
    @LogAnnotation(value = "查询用户列表")
    public ResponseVO users(UserConditionVO user) {
        //用户列表
        PageInfo<SysUser> userlist = userService.findUserListByCondition(user);
        return ResponseVO.success(userlist);
    }


}
