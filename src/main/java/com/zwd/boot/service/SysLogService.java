package com.zwd.boot.service;

import com.github.pagehelper.PageInfo;
import com.zwd.boot.common.base.AbstractService;
import com.zwd.boot.model.entity.SysLog;
import com.zwd.boot.model.vo.LogConditionVO;

/**
 * @author 随风逐梦
 */
public interface SysLogService extends AbstractService<SysLog,Long> {

    /**
     * 异步更新日志
     * @param log
     */
    void asyncUpdateSystemLog(SysLog log);

    /**
     * 异步添加日志
     * @param log
     */
    void asyncSaveSystemLog(SysLog log);

    /**
     * 分页获取
     * @param sysLog
     * @return
     */
    PageInfo<SysLog> findLogListByCondition(LogConditionVO sysLog);
}
