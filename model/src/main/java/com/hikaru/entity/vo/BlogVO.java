package com.hikaru.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 前后台并用的博客视图对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogVO {

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
     * 博客内容
     */
    private String content;

    /**
     * 博客头图
     */
    private String firstPicture;

    /**
     * 博客类型
     */
    private String type;

    /**
     * 赞赏是否开启（0：关闭 1：开启）
     */
    private Integer appreciation;

    /**
     * 浏览次数
     */
    private Integer views;

    /**
     * 转载声明是否开启（0：关闭 1：开启）
     */
    private Integer shareStatement;

    /**
     * 评论是否开启（0：关闭 1：开启）
     */
    private Integer commentAbled;

    /**
     * 是否发布（0：存为草稿 1：发布）
     */
    private Integer published;

    /**
     * 是否推荐（0：不推荐 1：推荐）
     */
    private Integer recommend;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 博客分类
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long blogTypeId;

    /**
     * 博客分类名
     */
    private String blogTypeName;

    /**
     * 博客标签
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long blogLabelId;

    /**
     * 博客标签名
     */
    private String blogLabelName;

}
