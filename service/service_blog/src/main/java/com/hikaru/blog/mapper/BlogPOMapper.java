package com.hikaru.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hikaru.entity.po.BlogPO;
import com.hikaru.entity.vo.BlogCommentVO;
import com.hikaru.entity.vo.BlogManageVO;
import com.hikaru.entity.vo.BlogVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author admin
* @description 针对表【t_blog】的数据库操作Mapper
* @createDate 2022-11-02 14:43:53
* @Entity com.hikaru.blog.entity.po.BlogPO
*/
@Mapper
public interface BlogPOMapper extends BaseMapper<BlogPO> {
    void insertInnerBlogType(@Param("blogId") Long blogId, @Param("typeId") Long typeId);
    void insertInnerBlogLabel(@Param("blogId") Long blogId, @Param("labelId") Long labelId);
    Page<BlogManageVO> getBlogManageVOInfoList(@Param("page")Page<BlogManageVO> page);
    Page<BlogVO> selectBlogPageInfoByTypeId(@Param("page") Page<BlogVO> page, @Param("typeId") Long typeId);
    Page<BlogVO> selectBlogPageInfoByLabelId(@Param("page") Page<BlogVO> page, @Param("labelId") Long labelId);
    Page<BlogVO> selectAllBlogPageInfo(@Param("page") Page<BlogVO> page);
    List<BlogCommentVO> selectBlogCommentInfoByBlogId(@Param("blogId") Long blogId);
    void insertInnerCommentBlog(@Param("blogId") Long blogId, @Param("commentId") Long commentId);
}
