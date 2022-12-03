package com.hikaru.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hikaru.entity.po.LabelPO;
import com.hikaru.entity.vo.ContentLabelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author admin
* @description 针对表【t_label】的数据库操作Mapper
* @createDate 2022-11-03 20:40:19
* @Entity com.hikaru.blog.entity.po.LabelPO
*/
@Mapper
public interface LabelPOMapper extends BaseMapper<LabelPO> {
    Page<ContentLabelVO> selectLabelContentPage(@Param("page") Page<ContentLabelVO> page);
    List<ContentLabelVO> selectLabelContentInfo();
}




