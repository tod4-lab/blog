package com.hikaru.serviceUtil.exception;

import com.hikaru.common.constance.AuthConstance;
import com.hikaru.common.result.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
@Slf4j
public class GlobalException {
    @ExceptionHandler(AuthenticationException.class)
    public void authenticationExceptionHandle(AuthenticationException e) {
        throw e;
    }

    @ExceptionHandler(AccessDeniedException.class)
    public void authenticationExceptionHandle(AccessDeniedException e) {
        throw e;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R<Object> exceptionHandle(Exception e) {
        e.printStackTrace();
        log.error(e.getMessage());
        return R.fail(AuthConstance.MESSAGE_SYSTEM_ERROR);
    }
    @ExceptionHandler(AuthException.class)
    @ResponseBody
    public R<Object> authExceptionHandle(AuthException e) {
        log.error(e.getMessage());
        return R.fail(e.getCode(), e.getMsg());
    }

}
