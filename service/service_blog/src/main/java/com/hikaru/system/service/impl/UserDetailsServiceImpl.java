package com.hikaru.system.service.impl;

import com.hikaru.common.constance.AuthConstance;
import com.hikaru.common.constance.ResultCode;
import com.hikaru.entity.SysUser;
import com.hikaru.serviceUtil.exception.AuthException;
import com.hikaru.springSecurity.custom.CustomUser;
import com.hikaru.system.service.IndexService;
import com.hikaru.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    SysUserService userService;
    @Autowired
    IndexService indexService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SysUser sysUser = userService.getSysUserByUserName(s);

        if(sysUser == null) {
            throw new AuthException(ResultCode.ERROR,
                    AuthConstance.MESSAGE_USER_NAME_OR_PASSWORD_NOT_RIGHT);
        }

        if(sysUser.getStatus() == 0) {
            throw new AuthException(ResultCode.ERROR,
                    AuthConstance.MESSAGE_USER_CAN_NOT_USE);
        }
        // TODO 查询权限信息
        Long id = sysUser.getId();
        List<String> authorityListByUserId = indexService.getAuthorityListByUserId(id);

        return new CustomUser(sysUser, authorityListByUserId);
    }

}
