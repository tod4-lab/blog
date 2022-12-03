package com.hikaru.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hikaru.common.result.R;
import com.hikaru.entity.SysMenu;
import com.hikaru.serviceUtil.RedisCache;
import com.hikaru.system.service.SysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sys/menu")
@Slf4j
public class SysMenuController {
    @Autowired
    SysMenuService menuService;

    @Autowired
    RedisCache redisCache;

    /**
     * 获取全部的菜单信息
     * @return
     */
    @GetMapping("")
    public R<List<SysMenu>> getWholeMenuTreeHandle() {
        List<SysMenu> returnList = new ArrayList<>();

        LambdaQueryWrapper<SysMenu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(SysMenu::getId);

        List<SysMenu> list = menuService.list(queryWrapper);

        Map<Long, SysMenu> menuMap = new HashMap<>();

        for(SysMenu menu : list) {
            menuMap.put(menu.getId(), menu);
        }

        for(SysMenu menu : list) {
            Long parentId = menu.getParentId();
            if(parentId == 0) {
                returnList.add(menu);
            } else {
                SysMenu parentMenu = menuMap.get(parentId);
                parentMenu.getChildren().add(menu);
            }
        }

        return R.successWithData(returnList);
    }

}
