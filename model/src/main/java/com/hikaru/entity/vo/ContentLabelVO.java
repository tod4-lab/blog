package com.hikaru.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 前后台标签简要信息视图对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContentLabelVO {
    /**
     *
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 标签名
     */
    private String name;

    /**
     * 博客数量
     */
    private Integer blogNum;

    /**
     * 创建时间
     */
    private String createTime;

}
