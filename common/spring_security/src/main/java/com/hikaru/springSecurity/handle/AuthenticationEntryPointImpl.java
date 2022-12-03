package com.hikaru.springSecurity.handle;

import com.hikaru.common.constance.AuthConstance;
import com.hikaru.common.result.R;
import com.hikaru.common.utils.JWTConstant;
import com.hikaru.common.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse response,
                         AuthenticationException e) throws IOException, ServletException {
        ResponseUtil.out(response, R.fail(HttpStatus.UNAUTHORIZED.value(),
                AuthConstance.MESSAGE_USER_AUTH_FAILED));
    }
}
