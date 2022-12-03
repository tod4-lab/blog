package com.hikaru.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hikaru.entity.SysRole;
import com.hikaru.entity.SysUser;

import java.util.List;

/**
* @author admin
* @description 针对表【sys_user(用户表)】的数据库操作Service
* @createDate 2022-11-10 21:05:09
*/
public interface SysUserService extends IService<SysUser> {
    List<SysRole> getRoleInfoListByUserId(Long userId);
    SysUser getSysUserByUserName(String userName);
    void deleteRoleInfoByUserId(Long userId);
}
