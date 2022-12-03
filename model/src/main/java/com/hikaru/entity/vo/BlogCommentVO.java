package com.hikaru.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogCommentVO {
    /**
     *
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 评论昵称
     */
    private String nickName;

    /**
     * 评论的临时邮箱
     */
    private String email;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 头像
     */
    private String avatar;


    /**
     * 评论时间
     */
    private String createTime;

    /**
     * 上级评论Id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;

    /**
     * 博客id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long blogId;

    @TableField(exist = false)
    private List<BlogCommentVO> children = new ArrayList<>();
}
