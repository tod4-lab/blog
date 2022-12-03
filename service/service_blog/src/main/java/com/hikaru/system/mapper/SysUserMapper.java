package com.hikaru.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hikaru.entity.SysRole;
import com.hikaru.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author admin
* @description 针对表【sys_user(用户表)】的数据库操作Mapper
* @createDate 2022-11-10 21:05:09
* @Entity com.hikaru.entity.SysUser
*/
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    List<SysRole> selectRoleIdListByUserId(@Param("userId") Long userId);
    void deleteUserRoleInfo(@Param("userId") Long userId);
}




