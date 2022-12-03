package com.hikaru.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hikaru.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author admin
* @description 针对表【sys_role(角色)】的数据库操作Mapper
* @createDate 2022-11-10 19:44:31
* @Entity com.hikaru.entity.SysRole
*/
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
    List<Long> selectMenuIdListByRoleId(@Param("roleId") Long roleId);
    void insertMenuRoleInfo(@Param("roleId") Long roleId, @Param("menuId") Long menuId);
    void deleteAllMenuRoleByRoleId(@Param("roleId") Long roleId);
    List<SysRole> selectRoleInfoListByUserId(@Param("userId") Long userId);
    void deleteUserRoleInfo(@Param("userId") Long userId);
    void insertUserRoleInfo(@Param("roleId") Long roleId, @Param("userId") Long userId);
}




