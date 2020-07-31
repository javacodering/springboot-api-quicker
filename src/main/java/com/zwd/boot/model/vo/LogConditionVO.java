package com.zwd.boot.model.vo;

import lombok.Data;

/**
 * @author 随风逐梦
 * @create 2020-07-21 17:29
 */
@Data
public class LogConditionVO extends PageConditionVO {
    private Long userId;
    private String logLevel;
    private String type;
}
