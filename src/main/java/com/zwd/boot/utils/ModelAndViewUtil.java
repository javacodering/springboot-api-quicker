package com.zwd.boot.utils;

import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 接口返回工具类
 * @author 随风逐梦
 */
public class ModelAndViewUtil {

    public static ModelAndView view(String view) {
        return new ModelAndView(view);
    }

    public static ModelAndView view(String view, Map<String, Object> model) {
        return new ModelAndView(view, model);
    }

    public static ModelAndView redirect(String view) {
        return new ModelAndView("redirect:" + view);
    }

    public static ModelAndView forward(String view) {
        return new ModelAndView("forward:" + view);
    }





}
