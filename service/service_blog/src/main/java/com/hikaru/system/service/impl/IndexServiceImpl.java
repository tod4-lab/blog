package com.hikaru.system.service.impl;

import com.hikaru.common.constance.AuthConstance;
import com.hikaru.common.constance.ResultCode;
import com.hikaru.common.utils.JWTConstant;
import com.hikaru.common.utils.JWTUtil;
import com.hikaru.entity.SysMenu;
import com.hikaru.entity.SysRole;
import com.hikaru.entity.SysUser;
import com.hikaru.entity.vo.LoginVO;
import com.hikaru.serviceUtil.RedisCache;
import com.hikaru.serviceUtil.exception.AuthException;
import com.hikaru.springSecurity.custom.CustomUser;
import com.hikaru.system.mapper.SysMenuMapper;
import com.hikaru.system.mapper.SysRoleMapper;
import com.hikaru.system.service.IndexService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class IndexServiceImpl implements IndexService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    SysRoleMapper sysRoleMapper;

    @Autowired
    SysMenuMapper sysMenuMapper;

    @Autowired
    RedisCache redisCache;

    @Override
    public Map<String, String> login(LoginVO loginVO) {
        // authenticationManager进行用户认证
        String username = loginVO.getUsername();
        String password = loginVO.getPassword();

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(username, password);

        // authenticationManager会调用UserDetailService进行用户账户和密码的校验
        Authentication authenticate =
                authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        // 认证失败
        if (Objects.isNull(authenticate)) {
            throw new AuthException(ResultCode.ERROR, AuthConstance.MESSAGE_USER_NAME_OR_PASSWORD_NOT_RIGHT);
        }

        // 这里返回的内容正是我们封装的UserDetail的查询结果
        CustomUser customUser = (CustomUser) authenticate.getPrincipal();

        SysUser sysUser = customUser.getSysUser();
        String id = sysUser.getId().toString();

        // 将用户信息放入redis
        String redisKey = AuthConstance.REDIS_TOKEN_PREFIX + id;
        redisCache.setCacheObject(redisKey, customUser,
                JWTConstant.JWT_TTL, JWTConstant.JWT_TIMEUNIT);

        // 返回生成的token
        String s = JWTUtil.createJWT(id);
        Map<String, String> token = new HashMap<>();
        token.put(AuthConstance.SYS_RETURN_TOKEN_NAME, s);

        return token;
    }

    @Override
    public void logOut(String token) {
        Claims claims = JWTUtil.parseJWT(token);
        String id = claims.getSubject();

        String redisKey = AuthConstance.REDIS_TOKEN_PREFIX + id;
        redisCache.deleteObject(redisKey);
    }

    @Override
    public List<String> getAuthorityListByUserId(Long userId) {
        List<String> list = new ArrayList<>();
        List<SysRole> roles = sysRoleMapper.selectRoleInfoListByUserId(userId);

        for(SysRole role : roles) {
            String roleCode = "ROLE_" + role.getRoleCode();
            list.add(roleCode);
            List<SysMenu> sysMenus = sysMenuMapper.selectMenuInfoListByRoleId(role.getId());
            for(SysMenu sysMenu : sysMenus) {
                Integer type = sysMenu.getType();
                if(2 == type) {
                    list.add(sysMenu.getPerms());
                }
            }
        }
        return list;
    }

    @Override
    public List<SysMenu> getMenuInfoListByPermsList(List<String> permsList) {
        return sysMenuMapper.selectMenuInfoListByPerms(permsList);
    }

}
