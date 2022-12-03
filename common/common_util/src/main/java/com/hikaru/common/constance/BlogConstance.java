package com.hikaru.common.constance;

public interface BlogConstance {
    Integer SUCCESS_CODE = 200;
    Integer FAILURE_CODE = 300;

    String DATE_FORMAT = "yyyy-MM-dd";

    String SYSTEM_ERROR_BLOG_PUBLISH_FAILURE = "系统错误：博客发送失败 请联系管理员";

    String SYSTEM_ERROR_BLOG_TYPE_ADD_FAILURE = "系统错误：博客分类添加失败 请联系管理员";
    String SYSTEM_ERROR_BLOG_TYPE_DELETE_FAILURE = "系统错误：博客分类删除失败 请联系管理员";
    String SYSTEM_ERROR_BLOG_TYPE_UPDATE_FAILURE = "系统错误：博客分类修改失败 请联系管理员";
    String SYSTEM_ERROR_LABEL_TYPE_ADD_FAILURE = "系统错误：博客标签添加失败 请联系管理员";
    String SYSTEM_ERROR_LABEL_TYPE_DELETE_FAILURE = "系统错误：博客标签删除失败 请联系管理员";
    String SYSTEM_ERROR_LABEL_TYPE_UPDATE_FAILURE = "系统错误：博客标签修改失败 请联系管理员";
}
