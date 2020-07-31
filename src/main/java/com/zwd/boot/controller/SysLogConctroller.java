package com.zwd.boot.controller;

import com.github.pagehelper.PageInfo;
import com.zwd.boot.common.annotation.LogAnnotation;
import com.zwd.boot.common.result.ResponseVO;
import com.zwd.boot.model.entity.SysLog;
import com.zwd.boot.model.vo.LogConditionVO;
import com.zwd.boot.service.SysLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 随风逐梦
 * @create 2020-07-20 9:52
 */
@Slf4j
@RestController
@RequestMapping(value = "/admin")
public class SysLogConctroller{

    @Resource
    private SysLogService sysLogService;

    @RequestMapping(value = "/log")
    @LogAnnotation(value = "获取日志列表")
    public ResponseVO getLogs(LogConditionVO sysLog){
        //用户列表
        PageInfo<SysLog> loglist = sysLogService.findLogListByCondition(sysLog);
        return ResponseVO.success(loglist);
    }
}
