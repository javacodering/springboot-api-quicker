package com.zwd.boot.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zwd.boot.mapper.SysUserMapper;
import com.zwd.boot.model.entity.SysUser;
import com.zwd.boot.model.vo.UserConditionVO;
import com.zwd.boot.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 随风逐梦
 */
@Service("UserService")
public class UserServiceImpl implements UserService{

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser insert(SysUser entity) {
        return null;
    }

    @Override
    public boolean removeByPrimaryKey(Long primaryKey) {
        return false;
    }

    @Override
    public boolean updateSelective(SysUser entity) {
        return false;
    }

    @Override
    public SysUser getByPrimaryKey(Long primaryKey) {
        return null;
    }

    @Override
    public PageInfo<SysUser> findUserListByCondition(UserConditionVO vo) {
        PageHelper.startPage(vo.getPageNumber(), vo.getPageSize());
        List<SysUser> sysUsers = sysUserMapper.findUserListByCondition(vo);
        if (CollectionUtils.isEmpty(sysUsers)) {
            return null;
        }
        PageInfo<SysUser> info = new PageInfo<>();
        info.setList(sysUsers);
        return info;
    }
}
