package com.hikaru.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户表
 * @TableName sys_user
 */
@TableName(value ="sys_user")
@Data
public class SysUser implements Serializable {
    /**
     * 会员id
     */
    @TableId(value = "id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 用户名
     */
    @TableField(value = "username")
    private String username;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 姓名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 手机
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 头像地址
     */
    @TableField(value = "head_url")
    private String headUrl;

    /**
     * 部门id
     */
    @TableField(value = "dept_id")
    private Long deptId;

    /**
     * 岗位id
     */
    @TableField(value = "post_id")
    private Long postId;

    /**
     * 描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 状态（1：正常 0：停用）
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private Date updateTime;

    /**
     * 删除标记（0:可用 1:已删除）
     */
    @TableLogic
    private Integer isDeleted;

}
