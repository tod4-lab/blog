package com.hikaru.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * 菜单表
 * @TableName sys_menu
 */
@TableName(value ="sys_menu")
@Data
public class SysMenu implements Serializable {
    /**
     * 编号
     */
    @TableId(value = "id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 所属上级
     */
    @TableField(value = "parent_id")
    private Long parentId;

    /**
     * 名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 类型(0:目录,1:菜单,2:按钮)
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 路由地址
     */
    @TableField(value = "path")
    private String path;

    /**
     * 组件路径
     */
    @TableField(value = "component")
    private String component;

    /**
     * 权限标识
     */
    @TableField(value = "perms")
    private String perms;

    /**
     * 图标
     */
    @TableField(value = "icon")
    private String icon;

    /**
     * 排序
     */
    @TableField(value = "sort_value")
    private Integer sortValue;

    /**
     * 状态(0:禁止,1:正常)
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 删除标记（0:可用 1:已删除）
     */
    @TableLogic
    private Integer isDeleted;

    @TableField(exist = false)
    private List<SysMenu> children = new ArrayList<>();

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
