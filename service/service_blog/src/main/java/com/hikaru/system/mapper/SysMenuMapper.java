package com.hikaru.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hikaru.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author admin
* @description 针对表【sys_menu(菜单表)】的数据库操作Mapper
* @createDate 2022-11-11 15:07:12
* @Entity com.hikaru.entity.SysMenu
*/
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    List<SysMenu> selectMenuInfoListByRoleId(@Param("roleId") Long roleId);
    List<SysMenu> selectMenuInfoListByPerms(@Param("permsList") List<String> permsList);
}




