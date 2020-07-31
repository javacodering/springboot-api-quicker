package com.zwd.boot.common.result;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zwd.boot.common.constant.CommonConst;
import com.zwd.boot.common.enums.ResponseCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;
import java.util.List;

/**
 * 接口返回工具类
 * @author 随风逐梦
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ResponseVO<T> {
    private Boolean success;
    private Integer status;
    private String message;
    private T data;

    public ResponseVO(Boolean success,Integer status, String message, T data) {
       this.success = success;
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResponseVO(ResponseCodeEnum status, T data) {
        this(status.getSuccess(),status.getCode(), status.getMessage(), data);
    }

    public static ResponseVO error(boolean success,int code, String message) {
        return vo(success,code, message, null);
    }

    public static ResponseVO error(ResponseCodeEnum status) {
        return vo(status.getSuccess(),status.getCode(), status.getMessage(), null);
    }

    public static ResponseVO error(String message) {
        return vo(Boolean.FALSE, CommonConst.DEFAULT_ERROR_CODE, message, null);
    }

    public static ResponseVO success(String message, Object data) {
        return vo(Boolean.TRUE,CommonConst.DEFAULT_SUCCESS_CODE, message, data);
    }

    public static ResponseVO success(Object data) {
        return vo(ResponseCodeEnum.SUCCESS.getSuccess(),ResponseCodeEnum.SUCCESS.getCode(), ResponseCodeEnum.SUCCESS.getMessage(), data);
    }

    public static ResponseVO success(String message) {
        return success(message, null);
    }

    public static ResponseVO success(ResponseCodeEnum status) {
        return vo(status.getSuccess(),status.getCode(), status.getMessage(), null);
    }

    public static ResponseVO vo(boolean success,int code, String message, Object data) {
        return new ResponseVO<>(success,code, message, data);
    }

    public String toJson() {
        T t = this.getData();
        if (t instanceof List || t instanceof Collection) {
            return JSONObject.toJSONString(this, SerializerFeature.WriteNullListAsEmpty);
        } else {
            return JSONObject.toJSONString(this, SerializerFeature.WriteMapNullValue);
        }
    }
}
