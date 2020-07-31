package com.zwd.boot.common.enums;

import com.zwd.boot.common.result.ResponseVO;

/**
 * @author 随风逐梦
 */
public enum ResponseCodeEnum {

    SUCCESS(true,200, "操作成功"),
    ERROR(false,500, "服务器未知错误"),
    INVALID_PARAMS(false,500, "参数校验异常：%s"),
    UPLOAD_FILE_ERROR(false,500, "文件上传失败"),
    ;

    private Boolean success;
    private Integer code;
    private String message;

    ResponseCodeEnum(Boolean success,Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public static ResponseCodeEnum getResponseStatus(String message) {
        for (ResponseCodeEnum ut : values()) {
            if (ut.getMessage() == message) {
                return ut;
            }
        }
        return null;
    }

    /**
     * 格式化
     * @param args
     * @return
     */
    public  ResponseVO formatArgs(Object... args) {
        boolean success = this.success;
        int code = this.code;
        String message = String.format(this.message, args);
        return new ResponseVO(success,code, message,null);
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Boolean getSuccess() {
        return success;
    }
}
