package com.hikaru.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;

/**
 *
 * @TableName t_type
 */
@TableName(value ="t_type")
@Data
public class TypePO implements Serializable {
    /**
     *
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "id")
    private Long id;

    /**
     * 类型名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private String createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private String updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
