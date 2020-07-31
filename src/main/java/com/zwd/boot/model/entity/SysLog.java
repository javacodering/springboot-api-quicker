package com.zwd.boot.model.entity;

import com.zwd.boot.common.base.BaseEntity;
import com.zwd.boot.common.enums.LogLevelEnum;
import com.zwd.boot.common.enums.LogTypeEnum;
import lombok.Data;

import javax.persistence.Table;

/**
 * @author 随风逐梦
 * @create 2020-07-20 20:02
 */
@Data
@Table(name = "sys_log")
public class SysLog extends BaseEntity {
    private String id;
    private Long userId;
    private String requestUrl;
    private String requestType;
    private String requestParams;
    private String ip;
    private Long costTime;
    private String logLevel;
    private String content;
    private String type;
    private String ua;
    private String os;
    private String browser;
    private String referer;
    private String exception;

    public void setLogLevel(LogLevelEnum logLevel) {
        if (null == logLevel) {
            return;
        }
        this.logLevel = logLevel.toString();
    }

    public void setType(LogTypeEnum type) {
        if (null == type) {
            return;
        }
        this.type = type.toString();
    }

}
