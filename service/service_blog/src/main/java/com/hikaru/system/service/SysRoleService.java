package com.hikaru.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hikaru.entity.SysRole;

import java.util.List;

/**
* @author admin
* @description 针对表【sys_role(角色)】的数据库操作Service
* @createDate 2022-11-10 19:44:31
*/
public interface SysRoleService extends IService<SysRole> {
    List<Long> getMenuIdListByRoleId(Long roleId);
    void updateAuthInfoByRoleId(Long roleId, List<Long> authIdList);
    void updateUserRoleInfo(Long userId, List<Long> roleIdList);
}
