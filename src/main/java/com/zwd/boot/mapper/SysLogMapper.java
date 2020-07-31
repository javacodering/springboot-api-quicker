package com.zwd.boot.mapper;

import com.zwd.boot.common.base.BaseMapper;
import com.zwd.boot.model.entity.SysLog;
import com.zwd.boot.model.vo.LogConditionVO;

import java.util.List;

/**
 * @author 随风逐梦
 */
public interface SysLogMapper extends BaseMapper<SysLog> {

    /**
     * 分页查询
     * @param sysLog
     * @return
     */
    List<SysLog> findLogListByCondition(LogConditionVO sysLog);
}
