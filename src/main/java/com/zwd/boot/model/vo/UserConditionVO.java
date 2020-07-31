package com.zwd.boot.model.vo;

import com.zwd.boot.model.entity.SysUser;
import lombok.Data;

/**
 * @author 随风逐梦
 * @create 2020-07-21 15:59
 */
@Data
public class UserConditionVO extends  PageConditionVO {
    private SysUser sysUser;
}
