package com.hikaru.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hikaru.entity.SysRole;
import com.hikaru.entity.SysUser;
import com.hikaru.system.mapper.SysUserMapper;
import com.hikaru.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author admin
 * @description 针对表【sys_user(用户表)】的数据库操作Service实现
 * @createDate 2022-11-10 21:05:09
 */
@Service
@Transactional
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
        implements SysUserService {

    @Autowired
    SysUserMapper userMapper;

    @Override
    public List<SysRole> getRoleInfoListByUserId(Long userId) {
        return userMapper.selectRoleIdListByUserId(userId);
    }

    @Override
    public SysUser getSysUserByUserName(String userName) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(!StringUtils.isBlank(userName),
                SysUser::getName,
                userName);

        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public void deleteRoleInfoByUserId(Long userId) {
        userMapper.deleteUserRoleInfo(userId);
    }

}




