package com.hikaru.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.List;

/**
 * 前台标签视图对象
 */
@Data
public class LabelVO {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String name;
    private String createTime;
    private String updateTime;

    private Integer blogNum;
    private List<BlogVO> blogList;

}
