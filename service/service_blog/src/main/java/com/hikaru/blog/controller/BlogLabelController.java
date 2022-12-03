package com.hikaru.blog.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hikaru.blog.service.BlogPOService;
import com.hikaru.common.result.R;
import com.hikaru.entity.vo.BlogVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blog")
@Slf4j
public class BlogLabelController {

    @Autowired
    BlogPOService blogPOService;


    /**
     * 前台根据标签id获取博客信息
     * @param cur
     * @param limit
     * @param labelId
     * @return
     */
    @GetMapping("/getBlogPageInfoByLabelId/{cur}/{limit}")
    public R<Page<BlogVO>> getBlogVOPageInfoByTypeIdRemote(@PathVariable Integer cur,
                                                           @PathVariable Integer limit,
                                                           @Param("labelId") Long labelId) {
        try {
            Page<BlogVO> blogPageInfoByLabelId = null;
            if(labelId == null) {
                blogPageInfoByLabelId = blogPOService.getAllBlogPageInfo(cur, limit);
            } else {
                blogPageInfoByLabelId = blogPOService.getBlogPageInfoByLabelId(cur, limit, labelId);
            }
            return R.successWithData(blogPageInfoByLabelId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return R.fail(null);
        }
    }
}
