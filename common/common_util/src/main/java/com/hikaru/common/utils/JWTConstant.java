package com.hikaru.common.utils;

import java.util.concurrent.TimeUnit;

public class JWTConstant {
    public static final Integer JWT_ERROR_CODE_NULL = 4000;  // token异地登录
    public static final Integer JWT_ERROR_CODE_EXPIRE = 4001;    // token过期
    public static final Integer JWT_ERROR_CODE_FAIL = 4002;  //token验证失败

    public static final String JWT_SECRET = "9b91643073cdda1d93507ec66591315c";
    public static final TimeUnit JWT_TIMEUNIT = TimeUnit.MILLISECONDS;
    public static final long JWT_TTL = 15 * 60 * 1000;
    public static final String JWT_USER = "tod4";
}
