package com.hikaru.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hikaru.entity.po.BlogPO;
import com.hikaru.entity.vo.BlogCommentVO;
import com.hikaru.entity.vo.BlogManageVO;
import com.hikaru.entity.vo.BlogVO;

import java.util.List;

/**
* @author admin
* @description 针对表【t_blog】的数据库操作Service
* @createDate 2022-11-02 14:43:53
*/
public interface BlogPOService extends IService<BlogPO> {
    void blogPublish(BlogVO blogVO);
    Page<BlogManageVO> getBlogManageVOInfoList(int cur, int limit);
    Page<BlogVO> getBlogPageInfoByTypeId(int cur, int limit, Long typeId);
    Page<BlogVO> getBlogPageInfoByLabelId(int cur, int limit, Long labelId);
    Page<BlogVO> getAllBlogPageInfo(int cur, int limit);
    List<BlogCommentVO> getCommentInfoListByBlogId(Long blogId);
    void addCommentById(Long blogId, Long commentId);
}
