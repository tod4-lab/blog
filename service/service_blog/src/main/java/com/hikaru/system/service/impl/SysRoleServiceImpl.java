package com.hikaru.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hikaru.entity.SysRole;
import com.hikaru.system.mapper.SysRoleMapper;
import com.hikaru.system.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author admin
* @description 针对表【sys_role(角色)】的数据库操作Service实现
* @createDate 2022-11-10 19:44:31
*/
@Service
@Transactional
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole>
    implements SysRoleService {

    @Autowired
    SysRoleMapper roleMapper;

    @Override
    public List<Long> getMenuIdListByRoleId(Long roleId) {

        return roleMapper.selectMenuIdListByRoleId(roleId);
    }

    @Override
    public void updateAuthInfoByRoleId(Long roleId, List<Long> authIdList) {
        roleMapper.deleteAllMenuRoleByRoleId(roleId);
        for(Long authId : authIdList) {
            roleMapper.insertMenuRoleInfo(roleId, authId);
        }
    }

    @Override
    public void updateUserRoleInfo(Long userId, List<Long> roleIdList) {
        roleMapper.deleteUserRoleInfo(userId);
        for(Long roleId : roleIdList) {
            roleMapper.insertUserRoleInfo(roleId, userId);
        }
    }
}




