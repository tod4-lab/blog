package com.hikaru.springSecurity.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hikaru.common.constance.AuthConstance;
import com.hikaru.common.result.R;
import com.hikaru.common.utils.CheckResult;
import com.hikaru.common.utils.JWTConstant;
import com.hikaru.common.utils.JWTUtil;
import com.hikaru.entity.SysUser;
import com.hikaru.serviceUtil.RedisCache;
import com.hikaru.serviceUtil.exception.AuthException;
import com.hikaru.springSecurity.custom.CustomUser;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

/**
 * 认证解析过滤器：每次请求前的过滤器
 * 用于获取请求头中的token，并将信息放入springSecurity上下文用于后序过滤器
 */

@Slf4j
@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    RedisCache redisCache;


    /**
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        log.info("RequestUri:" + requestURI);

        String token = request.getHeader(AuthConstance.SYS_RETURN_TOKEN_NAME);

        if (StringUtils.isBlank(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        CheckResult checkResult = JWTUtil.validateJwt(token);

        if(!checkResult.isSuccess()) {
            throw new RuntimeException();
        }

        Claims claims = JWTUtil.parseJWT(token);
        String id = claims.getSubject();

        String redisKey = AuthConstance.REDIS_TOKEN_PREFIX + id;
        Object object = redisCache.getCacheObject(redisKey);
        CustomUser customUser = JSONObject.parseObject(object.toString(), CustomUser.class);

        // 用户过期
        if (Objects.isNull(customUser)) {
            throw new RuntimeException();
        }

        log.info(customUser.getSysUser().toString());
        log.info(customUser.getAuthorities().toString());

        // 根据token封装认证信息
        // TODO 获取权限信息封装到authentication
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(customUser, null, customUser.getAuthorities());

        // 将认证信息放入SpringSecurity上下文中用于后序过滤器过滤
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }

}
