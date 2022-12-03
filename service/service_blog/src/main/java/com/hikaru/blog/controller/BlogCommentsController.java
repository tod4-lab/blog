package com.hikaru.blog.controller;

import com.hikaru.blog.service.BlogPOService;
import com.hikaru.blog.service.CommentPOService;
import com.hikaru.common.result.R;
import com.hikaru.entity.po.CommentPO;
import com.hikaru.entity.vo.BlogCommentVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog/comments")
@Slf4j
public class BlogCommentsController {

    @Autowired
    BlogPOService blogPOService;

    @Autowired
    CommentPOService commentPOService;

    /**
     * 获取博客评论
     * @param blogId
     * @return
     */
    @GetMapping("/{blogId}")
    public R<List<BlogCommentVO>> getBlogCommentsByBlogIdRemote(@PathVariable Long blogId) {
        try {
            List<BlogCommentVO> commentInfoListByBlogId = blogPOService.getCommentInfoListByBlogId(blogId);
            return R.successWithData(commentInfoListByBlogId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return R.fail(null);
        }
    }

    @PostMapping("/issue")
    public R<Object> issueBlogCommentRemote(@RequestBody BlogCommentVO blogCommentVO) {
        try {
            log.info(blogCommentVO.toString());

            CommentPO commentPO = new CommentPO();
            BeanUtils.copyProperties(blogCommentVO, commentPO);

            commentPOService.save(commentPO);
            Long commentId = commentPO.getId();
            Long blogId = blogCommentVO.getBlogId();
            blogPOService.addCommentById(blogId, commentId);

            return R.successWithoutData();

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return R.fail(null);
        }
    }
}
