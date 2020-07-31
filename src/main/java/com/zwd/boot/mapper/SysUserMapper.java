package com.zwd.boot.mapper;

import com.zwd.boot.common.base.BaseMapper;
import com.zwd.boot.model.entity.SysUser;
import com.zwd.boot.model.vo.UserConditionVO;

import java.util.List;

/**
 * @author 随风逐梦
 * @create 2020-07-21 15:51
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    List<SysUser> findUserListByCondition(UserConditionVO vo);
}
