package com.zwd.boot.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zwd.boot.mapper.SysLogMapper;
import com.zwd.boot.model.entity.SysLog;
import com.zwd.boot.model.vo.LogConditionVO;
import com.zwd.boot.service.SysLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 随风逐梦
 */
@Slf4j
@Service(value = "SysLogService")
public class SysLogServiceImpl implements SysLogService {

    @Resource
    private SysLogMapper sysLogMapper;

    @Async
    @Override
    public void asyncUpdateSystemLog(SysLog sysLog) {
        DateTime date = DateUtil.date();
        sysLog.setUpdateTime(date);
        this.updateSelective(sysLog);
    }

    @Async
    @Override
    public void asyncSaveSystemLog(SysLog sysLog) {
        DateTime date = DateUtil.date();
        sysLog.setCreateTime(date);
        sysLog.setUpdateTime(date);
        this.insert(sysLog);
    }

    @Override
    public PageInfo<SysLog> findLogListByCondition(LogConditionVO sysLog) {
        PageHelper.startPage(sysLog.getPageNumber(), sysLog.getPageSize());
        List<SysLog> sysLogList = sysLogMapper.findLogListByCondition(sysLog);
        if (CollectionUtils.isEmpty(sysLogList)) {
            return null;
        }
        PageInfo<SysLog> info = new PageInfo<>();
        info.setList(sysLogList);
        return info;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SysLog insert(SysLog sysLog) {
        Assert.notNull(sysLog, "sysLog不可为空！");
        sysLogMapper.insertSelective(sysLog);
        return sysLog;
    }

    @Override
    public void insertList(List<SysLog> entities) {

    }

    @Override
    public boolean removeByPrimaryKey(Long primaryKey) {
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateSelective(SysLog sysLog) {
        Assert.notNull(sysLog, "sysLog不可为空！");
        return sysLogMapper.updateByPrimaryKey(sysLog) > 0;
    }

    @Override
    public SysLog getByPrimaryKey(Long primaryKey) {
        return null;
    }

    @Override
    public List<SysLog> listAll() {
        return null;
    }
}
