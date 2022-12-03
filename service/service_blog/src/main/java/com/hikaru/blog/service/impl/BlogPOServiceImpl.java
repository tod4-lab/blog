package com.hikaru.blog.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hikaru.blog.mapper.BlogPOMapper;
import com.hikaru.blog.service.BlogPOService;
import com.hikaru.entity.po.BlogPO;
import com.hikaru.entity.vo.BlogCommentVO;
import com.hikaru.entity.vo.BlogManageVO;
import com.hikaru.entity.vo.BlogVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author admin
 * @description 针对表【t_blog】的数据库操作Service实现
 * @createDate 2022-11-02 14:43:53
 */
@Service
@Transactional
@Slf4j
public class BlogPOServiceImpl extends ServiceImpl<BlogPOMapper, BlogPO>
        implements BlogPOService {
    @Autowired
    BlogPOMapper blogPOMapper;

    @Override
    public void blogPublish(BlogVO blogVO) {
        BlogPO blogPO = new BlogPO();
        BeanUtils.copyProperties(blogVO, blogPO);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date());

        blogPO.setUpdateTime(date);
        blogPO.setCreateTime(date);
        blogPO.setLikes(0);
        blogPO.setViews(0);

        blogPOMapper.insert(blogPO);

        Long blogPOId = blogPO.getId();
        log.info(blogPOId.toString());

        Long blogTypeId = blogVO.getBlogTypeId();
        blogPOMapper.insertInnerBlogType(blogPOId, blogTypeId);

        Long blogLabelId = blogVO.getBlogLabelId();
        blogPOMapper.insertInnerBlogLabel(blogPOId, blogLabelId);


    }


    @Override
    public Page<BlogManageVO> getBlogManageVOInfoList(int cur, int limit) {
        Page<BlogManageVO> page = new Page<>(cur, limit);
        return blogPOMapper.getBlogManageVOInfoList(page);
    }

    @Override
    public Page<BlogVO> getBlogPageInfoByTypeId(int cur, int limit, Long typeId) {
        Page<BlogVO> page = new Page<>(cur, limit);
        return blogPOMapper.selectBlogPageInfoByTypeId(page, typeId);
    }

    @Override
    public Page<BlogVO> getBlogPageInfoByLabelId(int cur, int limit, Long labelId) {
        Page<BlogVO> page = new Page<>(cur, limit);
        return blogPOMapper.selectBlogPageInfoByLabelId(page, labelId);
    }

    @Override
    public Page<BlogVO> getAllBlogPageInfo(int cur, int limit) {
        Page<BlogVO> page = new Page<>(cur, limit);
        return blogPOMapper.selectAllBlogPageInfo(page);
    }

    @Override
    public List<BlogCommentVO> getCommentInfoListByBlogId(Long blogId) {
        List<BlogCommentVO> blogCommentVOS = blogPOMapper.selectBlogCommentInfoByBlogId(blogId);
        List<BlogCommentVO> returnList = new ArrayList<>();

        Map<Long, BlogCommentVO> map = new HashMap<>();

        for(BlogCommentVO blogCommentVO : blogCommentVOS) {
            map.put(blogCommentVO.getId(), blogCommentVO);
        }

        for(BlogCommentVO blogCommentVO : blogCommentVOS) {
            Long parentId = blogCommentVO.getParentId();

            BlogCommentVO parentComment = null;

            while(parentId != null) {
                parentComment = map.get(parentId);
                parentId = parentComment.getParentId();
            }

            if(parentComment != null) {
                parentComment.getChildren().add(blogCommentVO);
            }
        }

        for(BlogCommentVO blogCommentVO : blogCommentVOS) {
            if(blogCommentVO.getParentId() == null) {
                returnList.add(blogCommentVO);
            }
        }

        return returnList;
    }

    @Override
    public void addCommentById(Long blogId, Long commentId) {
        blogPOMapper.insertInnerCommentBlog(blogId, commentId);
    }


}
