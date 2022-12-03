package com.hikaru.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 博客管理视图对象
 * 用于博客后台信息管理
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BlogManageVO {

    /**
     *
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 博客标题
     */
    private String title;

    /**
     * 博客头图
     */
    private String firstPicture;

    /**
     * 博客分类
     */
    private String blogTypeName;

    /**
     * 博客分类
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long blogTypeId;

    /**
     * 浏览次数
     */
    private Integer views;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 博客类型
     */
    private String type;

    /**
     * 点赞量
     */
    private Integer likes;


}
