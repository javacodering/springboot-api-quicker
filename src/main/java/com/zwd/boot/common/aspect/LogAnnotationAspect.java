package com.zwd.boot.common.aspect;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.zwd.boot.common.annotation.LogAnnotation;
import com.zwd.boot.holder.RequestHolder;
import com.zwd.boot.model.entity.SysLog;
import com.zwd.boot.model.entity.SysUser;
import com.zwd.boot.common.enums.AspectUtil;
import com.zwd.boot.common.enums.LogLevelEnum;
import com.zwd.boot.common.enums.LogTypeEnum;
import com.zwd.boot.common.enums.PlatformEnum;
import com.zwd.boot.service.SysLogService;
import com.zwd.boot.utils.RequestUtil;
import com.zwd.boot.utils.ThreadPoolUtil;
import com.zwd.boot.utils.UuidUtils;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *日志切面
 * @author 随风逐梦
 */
@Slf4j
@Aspect
@Component
public class LogAnnotationAspect {

    private static final ThreadLocal<Date> beginTimeThreadLocal = new NamedThreadLocal<Date>("ThreadLocal beginTime");

    private static final ThreadLocal<SysLog> logThreadLocal = new InheritableThreadLocal<SysLog>();

    private static final ThreadLocal<SysUser> currentUser=new InheritableThreadLocal<SysUser>();

    @Resource
    private SysLogService logService;


    @Pointcut(value = "@annotation(com.zwd.boot.common.annotation.LogAnnotation)")
    public void pointcut() {

    }

    /**
     * 前置通知 (在方法执行之前返回)用于拦截Controller层记录用户的操作的开始时间
     */
    @Before("pointcut()")
    public void doBefore(JoinPoint joinPoint){
        Date beginTime = new Date();
        beginTimeThreadLocal.set(beginTime);
        //debug模式下 显式打印开始时间用于调试
        if (log.isDebugEnabled()){
            log.debug("开始计时: {}  URI: {}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
                    .format(beginTime), RequestUtil.getRequestUrl());
        }
        //读取session中的用户
        HttpSession session = RequestHolder.getSession();
        SysUser user = (SysUser) session.getAttribute("admin_user");
        currentUser.set(user);
    }

    @After("pointcut()")
    public void after(JoinPoint point){
            Method currentMethod = AspectUtil.INSTANCE.getMethod(point);
            //获取操作名称
            LogAnnotation annotation = currentMethod.getAnnotation(LogAnnotation.class);
            boolean save = annotation.save();
            PlatformEnum platform = annotation.platform();
            String bussinessName = AspectUtil.INSTANCE.parseParams(point.getArgs(), annotation.value());

            SysLog sysLog = this.packageLog(platform, bussinessName);

            //请求开始时间
            long beginTime = beginTimeThreadLocal.get().getTime();
            long endTime = System.currentTimeMillis();
            //请求耗时
            Long logElapsedTime = endTime - beginTime;
            sysLog.setCostTime(logElapsedTime);

            // debu模式下打印JVM信息
            if (log.isDebugEnabled()) {
                log.debug("计时结束：{}  URL: {}  耗时： {}   最大内存: {}m  已分配内存: {}m  已分配内存中的剩余空间: {}m  最大可用内存: {}m",
                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(endTime),
                        RequestUtil.getRequestUrl(),
                        DateUtil.formatBetween(endTime - beginTime),
                        Runtime.getRuntime().maxMemory() / 1024 / 1024,
                        Runtime.getRuntime().totalMemory() / 1024 / 1024,
                        Runtime.getRuntime().freeMemory() / 1024 / 1024,
                        (Runtime.getRuntime().maxMemory() - Runtime.getRuntime().totalMemory() + Runtime.getRuntime().freeMemory()) / 1024 / 1024);
            }
            //线程池
            ThreadPoolUtil.getPool().execute(new SaveSystemLogThread(sysLog, logService));
            logThreadLocal.set(sysLog);

    }
    /**
     *  异常通知
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "pointcut()", throwing = "e")
    public  void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        SysLog log = logThreadLocal.get();
        if(log != null){
            log.setType(LogTypeEnum.ERROR);//日志类型
            log.setLogLevel(LogLevelEnum.ERROR);//日志等级
            log.setException(e.toString());
            new UpdateLogThread(log, logService).start();
        }
    }
    /**
     * 保存日志至数据库
     */
    private static class SaveSystemLogThread implements Runnable {

        private SysLog log;
        private SysLogService logService;

        public SaveSystemLogThread(SysLog esLog, SysLogService logService) {
            this.log = esLog;
            this.logService = logService;
        }

        @Override
        public void run() {
            logService.asyncSaveSystemLog(log);
        }
    }

    /**
     * 日志更新线程
     */
    private static class UpdateLogThread extends Thread {
        private SysLog syslog;
        private SysLogService logService;

        public UpdateLogThread(SysLog log, SysLogService logService) {
            super(UpdateLogThread.class.getSimpleName());
            this.syslog = log;
            this.logService = logService;
        }

        @Override
        public void run() {
            logService.asyncUpdateSystemLog(syslog);
        }
    }

    /**
     * 封装日志对象
     * @param platform
     * @param bussinessName
     * @return
     */
    private SysLog packageLog( PlatformEnum platform,String bussinessName){
        SysUser user = currentUser.get();
        //登入login操作 前置通知时用户未校验 所以session中不存在用户信息
        if(user == null){
            HttpSession session = RequestHolder.getSession();
            user = (SysUser) session.getAttribute("admin_user");
        }
        String ua = RequestUtil.getUa();
        SysLog sysLog = new SysLog();
        sysLog.setId(UuidUtils.creatUUID());
        sysLog.setLogLevel(LogLevelEnum.INFO);
        sysLog.setType(platform.equals(PlatformEnum.WEB) ? LogTypeEnum.VISIT : LogTypeEnum.SYSTEM);
        sysLog.setIp(RequestUtil.getIp());
        sysLog.setReferer(RequestUtil.getReferer());
        sysLog.setRequestUrl(RequestUtil.getRequestUrl());
        sysLog.setRequestType(RequestUtil.getMethod());
        sysLog.setUa(ua);
        sysLog.setRequestParams(JSONObject.toJSONString(RequestUtil.getParametersMap()));
        if (user != null) {
            sysLog.setUserId(user.getId());
            sysLog.setContent(String.format("用户: [%s] | 操作: %s", user.getUsername(), bussinessName));
        } else {
            sysLog.setContent(String.format("访客: [%s] | 操作: %s", sysLog.getIp(), bussinessName));
        }
        UserAgent agent = UserAgent.parseUserAgentString(ua);
        sysLog.setBrowser(agent.getBrowser().getName());
        sysLog.setOs(agent.getOperatingSystem().getName());
        sysLog.setCreateBy(LogTypeEnum.SYSTEM.toString());
        sysLog.setUpdateBy(LogTypeEnum.SYSTEM.toString());
        return sysLog;
    }
}
