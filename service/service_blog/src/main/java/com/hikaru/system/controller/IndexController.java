package com.hikaru.system.controller;

import com.alibaba.fastjson2.JSONObject;
import com.hikaru.common.constance.AuthConstance;
import com.hikaru.common.result.R;
import com.hikaru.common.utils.JWTUtil;
import com.hikaru.entity.SysMenu;
import com.hikaru.entity.SysUser;
import com.hikaru.entity.vo.LoginVO;
import com.hikaru.serviceUtil.RedisCache;
import com.hikaru.springSecurity.custom.CustomUser;
import com.hikaru.system.service.IndexService;
import com.hikaru.system.service.SysUserService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/sys")
@Slf4j
public class IndexController {
    @Autowired
    SysUserService userService;

    @Autowired
    IndexService indexService;

    @Autowired
    RedisCache redisCache;

    @GetMapping("/test")
    @PreAuthorize("hasAuthority('ceo')")
    public String test() {
        return "test";
    }


    /**
     * 用户登录
     * @param loginVO
     * @return
     */
    @PostMapping("/login")
    public R<Object> loginHandle(@RequestBody LoginVO loginVO) {

        Map<String, String> token = indexService.login(loginVO);
        return R.successWithData(token);
    }

    /**
     * 退出登录
     * @param token
     * @return
     */
    @GetMapping("/logout")
    public R<Object> logoutHandle(@RequestHeader(AuthConstance.SYS_RETURN_TOKEN_NAME) String token) {
        indexService.logOut(token);
        return R.successWithoutData();
    }

    /**
     * 获取登录信息
     * @return
     */
    @GetMapping("/userInfo")
    public R<Object> getUserInfoHandle(@RequestHeader("token") String token) {

        Claims claims = JWTUtil.parseJWT(token);
        String id = claims.getSubject();

        // redis获取用户信息
        String redisKey = AuthConstance.REDIS_TOKEN_PREFIX + id;
        Object object = redisCache.getCacheObject(redisKey);

        CustomUser customUser = JSONObject.parseObject(object.toString(), CustomUser.class);
        SysUser sysUser = customUser.getSysUser();

        Collection<? extends GrantedAuthority> authorities = customUser.getAuthorities();
        List<String> btnAuthList = new ArrayList<>();
        for(GrantedAuthority grantedAuthority : authorities) {
            String authority = grantedAuthority.getAuthority();
            btnAuthList.add(authority);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("name", sysUser.getName());
        map.put("avatar", sysUser.getHeadUrl());
        map.put("btnAuth", btnAuthList);

        return R.successWithData(map);
    }

    /**
     * 获取用户的菜单信息
     * @param token
     * @return
     */
    @GetMapping("/menuAuthInfo")
    public R<List<SysMenu>> getUserAuthInfoHandle(@RequestHeader("token") String token) {
        log.info(token);
        Claims claims = JWTUtil.parseJWT(token);
        String id = claims.getSubject();

        // redis获取用户的角色信息
        String redisKey = AuthConstance.REDIS_TOKEN_PREFIX + id;
        Object object = redisCache.getCacheObject(redisKey);
        CustomUser customUser = JSONObject.parseObject(object.toString(), CustomUser.class);
        Collection<? extends GrantedAuthority> authorities = customUser.getAuthorities();

        List<String> permsList = new ArrayList<>();
        for(GrantedAuthority grantedAuthority : authorities) {
            String authority = grantedAuthority.getAuthority();
            if(!authority.startsWith("ROLE_")) {
                permsList.add(authority);
            }
        }
        log.info(permsList.toString());

        List<SysMenu> list = indexService.getMenuInfoListByPermsList(permsList);

        List<SysMenu> returnList = new ArrayList<>();

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
