package com.zwd.boot.common.enums;

import com.alibaba.fastjson.JSON;
import com.zwd.boot.exception.CustomException;
import com.zwd.boot.utils.RegexUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author 随风逐梦
 */
public enum AspectUtil {
    INSTANCE;
    /**
     * 获取当前切面执行的方法的方法名
     * @param point 当前切面执行的方法
     */
    public Method getMethod(JoinPoint point){
        try {
            MethodSignature msig = (MethodSignature)point.getSignature();
            Object target = point.getTarget();
            Method method = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
            return method;
        }catch (Exception e){
            throw  new CustomException(e.getMessage());
        }
    }

    public String parseParams(Object[] params, String bussinessName) {
        if (bussinessName.contains("{") && bussinessName.contains("}")) {
            List<String> result = RegexUtil.match(bussinessName, "(?<=\\{)(\\d+)");
            for (String s : result) {
                int index = Integer.parseInt(s);
                bussinessName = bussinessName.replaceAll("\\{" + index + "}", JSON.toJSONString(params[index - 1]));
            }
        }
        return bussinessName;
    }
}
