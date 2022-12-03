package com.hikaru.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 *
 * @TableName t_comment
 */
@TableName(value ="t_comment")
@Data
public class CommentPO implements Serializable {
    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 评论昵称
     */
    @TableField(value = "nick_name")
    private String nickName;

    /**
     * 评论的临时邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 评论内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 头像
     */
    @TableField(value = "avatar")
    private String avatar;


    /**
     * 评论时间
     */
    @TableField(value = "create_time")
    private String createTime;

    @TableField(value = "parent_id")
    private Long parentId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
