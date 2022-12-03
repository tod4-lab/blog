package com.hikaru.springSecurity.handle;

import com.hikaru.common.constance.AuthConstance;
import com.hikaru.common.result.R;
import com.hikaru.common.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class AccessDeniedHandleImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest,
                       HttpServletResponse httpServletResponse,
                       AccessDeniedException e) throws IOException, ServletException {
        ResponseUtil.out(httpServletResponse,
                R.fail(HttpStatus.FORBIDDEN.value(),
                        AuthConstance.MESSAGE_FORBIDDEN));
    }
}
