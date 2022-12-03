package com.hikaru.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hikaru.entity.po.TypePO;
import com.hikaru.entity.vo.ContentTypeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author admin
* @description 针对表【t_type】的数据库操作Mapper
* @createDate 2022-11-03 20:40:29
* @Entity com.hikaru.blog.entity.po.TypePO
*/
@Mapper
public interface TypePOMapper extends BaseMapper<TypePO> {
    TypePO selectTypePOInfoByBlogId(@Param("blogId") Long blogId);
    Page<ContentTypeVO> selectContentTypePage(Page<ContentTypeVO> page);
    List<ContentTypeVO> selectContentTypeInfo();
}




