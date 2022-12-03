package com.hikaru.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 博客后台管理分类视图对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogManTypeVO {
    /**
     *
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 标签名
     */
    private String name;
}
