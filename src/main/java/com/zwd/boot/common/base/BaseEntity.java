package com.zwd.boot.common.base;

import com.zwd.boot.common.constant.CommonConst;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 随风逐梦
 * @create 2020-07-20 11:45
 */
@Data
public abstract class BaseEntity implements Serializable {
    /**
     * 删除状态：1删除，0未删除
     */
    private Integer delFlag = CommonConst.STATUS_NORMAL;

    /**
     * 创建人用户名
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

}
