package com.hikaru.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hikaru.common.result.R;
import com.hikaru.entity.SysMenu;
import com.hikaru.entity.SysRole;
import com.hikaru.serviceUtil.exception.AuthException;
import com.hikaru.system.service.SysMenuService;
import com.hikaru.system.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/sys/role")
@RestController
public class SysRoleController {
    @Autowired
    SysRoleService sysRoleService;

    @Autowired
    SysMenuService menuService;

    /**
     * 获取角色的无分页信息
     * @return
     */
    @GetMapping("/noPage")
    @PreAuthorize("hasAuthority('role:get')")
    public R<List<SysRole>> getRoleNoPageInfoHandle() {
        List<SysRole> list = sysRoleService.list();
        return R.successWithData(list);
    }

    /**
     * 角色分页信息查询
     *
     * @param cur
     * @param limit
     * @return
     */
    @GetMapping("/page/{cur}/{limit}")
    @PreAuthorize("hasAuthority('role:get')")
    public R<Page<SysRole>> getRolePageInfoHandle(@PathVariable Integer cur,
                                                  @PathVariable Integer limit) {

        Page<SysRole> page = new Page<>(cur, limit);
        Page<SysRole> pageInfo = sysRoleService.page(page);

        return R.successWithData(pageInfo);
    }

    /**
     * 关键词查询角色信息
     * @param keyWord
     * @param cur
     * @param limit
     * @return
     */
    @GetMapping("/keyWord/{keyWord}/{cur}/{limit}")
    @PreAuthorize("hasAuthority('role:get')")
    public R<Page<SysRole>> getRolePageInfoByKeyWordHandle(
            @PathVariable("keyWord") String keyWord, @PathVariable("cur") Integer cur,
            @PathVariable("limit") Integer limit
            ) {

        Page<SysRole> page = new Page<>(cur, limit);

        LambdaQueryWrapper<SysRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(!StringUtils.isBlank(keyWord), SysRole::getRoleName, keyWord)
                .or()
                .like(!StringUtils.isBlank(keyWord), SysRole::getRoleCode, keyWord)
                .or()
                .like(!StringUtils.isBlank(keyWord), SysRole::getDescription, keyWord);

        sysRoleService.page(page, queryWrapper);

        return R.successWithData(page);
    }

    /**
     * 角色添加
     *
     * @param role
     * @return
     */
    @PostMapping
    @PreAuthorize("hasAuthority('role:update')")
    public R<Object> addRoleInfoHandle(@RequestBody SysRole role) {
        try {
            sysRoleService.save(role);
        } catch (Exception e) {
            throw new AuthException();
        }
        return R.successWithoutData();
    }

    /**
     * 角色信息删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('role:delete')")
    public R<Object> deleteRoleInfoHandle(@PathVariable Long id) {
        try {
            sysRoleService.removeById(id);
        } catch (Exception e) {
            throw new AuthException();
        }
        return R.successWithoutData();
    }

    /**
     * 角色信息修改
     *
     * @param role
     * @return
     */
    @PutMapping
    @PreAuthorize("hasAuthority('role:update')")
    public R<Object> updateRoleInfoHandle(@RequestBody SysRole role) {
        try {
            sysRoleService.updateById(role);
        } catch (Exception e) {
            throw new AuthException();
        }
        return R.successWithoutData();
    }

    @GetMapping("/menu")
    @PreAuthorize("hasAuthority('role:get')")
    public R<List<SysMenu>> getMenuInfoListByRole() {
        List<SysMenu> list = menuService.list();
        return R.successWithData(list);
    }

    /**
     * 根据角色Id获取权限Id
     * @param roleId
     * @return
     */
    @GetMapping("/menu/{roleId}")
    @PreAuthorize("hasAuthority('role:get')")
    public R<List<Long>> getAuthIdListByRoleId(@PathVariable Long roleId) {
        return R.successWithData(sysRoleService.getMenuIdListByRoleId(roleId));
    }


    /**
     * 根据角色Id修改权限
     * @param roleId
     * @return
     */
    @PutMapping("/menu/{roleId}")
    @PreAuthorize("hasAuthority('auth:assign')")
    public R<Object> updateAuthInfoByRoleId(@PathVariable Long roleId,
                                                   @RequestBody List<Long> authIdList) {
        try {
            sysRoleService.updateAuthInfoByRoleId(roleId, authIdList);
        } catch (Exception e) {
            throw new AuthException();
        }
        return R.successWithoutData();
    }

    /**
     * 根据用户Id更新角色
     * @param userId
     * @param roleIdList
     * @return
     */
    @PostMapping("/{userId}")
    @PreAuthorize("hasAuthority('role:assign')")
    public R<Object> updateUserRoleInfoHandle(@PathVariable Long userId,
                                                @RequestBody List<Long> roleIdList) {
        sysRoleService.updateUserRoleInfo(userId, roleIdList);
        return R.successWithoutData();
    }
}
