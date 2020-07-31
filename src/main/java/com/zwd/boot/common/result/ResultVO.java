package com.zwd.boot.common.result;

import com.zwd.boot.common.enums.ResultCodeEnum;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 接口返回工具类链式书写
 * @author 随风逐梦
 */
@Data
public class ResultVO {
    private Boolean success;

    private Integer code;

    private String message;

    private Map<String, Object> data = new HashMap<>();

    // 构造器私有
    private ResultVO(){}

    // 通用返回成功
    public static ResultVO ok() {
        ResultVO r = new ResultVO();
        r.setSuccess(ResultCodeEnum.SUCCESS.getSuccess());
        r.setCode(ResultCodeEnum.SUCCESS.getCode());
        r.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return r;
    }

    // 通用返回失败，未知错误
    public static ResultVO error() {
        ResultVO r = new ResultVO();
        r.setSuccess(ResultCodeEnum.UNKNOWN_ERROR.getSuccess());
        r.setCode(ResultCodeEnum.UNKNOWN_ERROR.getCode());
        r.setMessage(ResultCodeEnum.UNKNOWN_ERROR.getMessage());
        return r;
    }

    // 设置结果，形参为结果枚举
    public static ResultVO setResult(ResultCodeEnum result) {
        ResultVO r = new ResultVO();
        r.setSuccess(result.getSuccess());
        r.setCode(result.getCode());
        r.setMessage(result.getMessage());
        return r;
    }

    /**------------使用链式编程，返回类本身-----------**/

    // 自定义返回数据
    public ResultVO data(Map<String,Object> map) {
        this.setData(map);
        return this;
    }

    // 通用设置data
    public ResultVO data(String key,Object value) {
        this.data.put(key, value);
        return this;
    }

    // 自定义状态信息
    public ResultVO message(String message) {
        this.setMessage(message);
        return this;
    }

    // 自定义状态码
    public ResultVO code(Integer code) {
        this.setCode(code);
        return this;
    }

    // 自定义返回结果
    public ResultVO success(Boolean success) {
        this.setSuccess(success);
        return this;
    }
}
