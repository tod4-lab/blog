package com.hikaru.common.constance;

public interface AuthConstance {
    String SYS_RETURN_TOKEN_NAME = "token";
    String REDIS_TOKEN_PREFIX = "TOKEN_PREFIX_";

    String MESSAGE_SYSTEM_ERROR = "系统错误，请联系管理员";
    String MESSAGE_USER_NAME_OR_PASSWORD_NOT_RIGHT = "用户名或密码错误";
    String MESSAGE_USER_AUTH_FAILED = "用户认证失败，请重新登录";
    String MESSAGE_USER_LOGIN_EXPIRE = "用户登录过期，请重新登录";
    String MESSAGE_PIC_UPLOAD_FAILURE = "图片上传失败";
    String MESSAGE_USER_NAME_ALREADY_EXISTS = "用户已存在，请重新输入";
    String MESSAGE_USER_CAN_NOT_USE = "用户被禁用，请联系管理员";
    String MESSAGE_FORBIDDEN = "没有访问的权限";
}
