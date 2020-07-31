package com.zwd.boot.exception;

import com.zwd.boot.common.enums.ResultCodeEnum;

/**
 * 自定义异常或业务异常
 * @author 随风逐梦
 */
public class CustomException extends RuntimeException {
    private Integer code;

    public CustomException(String message) {
        super(message);
    }
    public CustomException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public CustomException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    @Override
    public String toString() {
        return "CMSException{" + "code=" + code + ", message=" + this.getMessage() + '}';
    }
}
