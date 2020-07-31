package com.zwd.boot.service;

import com.github.pagehelper.PageInfo;
import com.zwd.boot.common.base.AbstractService;
import com.zwd.boot.model.vo.UserConditionVO;
import com.zwd.boot.model.entity.SysUser;

/**
 * @author 随风逐梦
 * @create 2020-07-20 10:07
 */
public interface UserService extends AbstractService<SysUser,Long> {

    PageInfo<SysUser> findUserListByCondition(UserConditionVO user);

}
