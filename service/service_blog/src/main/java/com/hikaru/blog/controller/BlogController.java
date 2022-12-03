package com.hikaru.blog.controller;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hikaru.blog.service.BlogPOService;
import com.hikaru.blog.service.LabelPOService;
import com.hikaru.blog.service.TypePOService;
import com.hikaru.common.constance.BlogConstance;
import com.hikaru.common.result.R;
import com.hikaru.entity.po.BlogPO;
import com.hikaru.entity.po.LabelPO;
import com.hikaru.entity.po.TypePO;
import com.hikaru.entity.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/blog")
@Slf4j
public class BlogController {

    @Autowired
    BlogPOService blogPOService;

    @Autowired
    TypePOService typePOService;

    @Autowired
    LabelPOService labelPOService;

    /**
     * 博客发布
     *
     * @param blogVO
     * @return
     */
    @RequestMapping("/publish")
    @PreAuthorize("hasAuthority('blog:update')")
    public R<Object> blogPublishHandleRemote(@RequestBody BlogVO blogVO) {

        try {
            blogPOService.blogPublish(blogVO);
            return R.successWithoutData();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return R.fail(BlogConstance.SYSTEM_ERROR_BLOG_PUBLISH_FAILURE);
        }

    }

    /**
     * 通过id获取博客
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    //@PreAuthorize("hasAuthority('blog:get')")
    public R<BlogVO> getBlogInfoById(@PathVariable Long id) {
        try {
            BlogPO blogPO = blogPOService.getById(id);
            BlogVO blogVO = new BlogVO();
            BeanUtils.copyProperties(blogPO, blogVO);

            return R.successWithData(blogVO);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return R.fail(null);
        }
    }

    /**
     * 获取全部博客信息
     *
     * @return
     */
    @RequestMapping(value = "/{cur}/{limit}", method = RequestMethod.GET)
    //@PreAuthorize("hasAuthority('blog:get')")
    public R<Page<BlogVO>> getAllBlogInfoPage(@PathVariable("cur") int cur,
                                              @PathVariable("limit") int limit) {
        try {
            Page<BlogVO> pageInfo = blogPOService.getAllBlogPageInfo(cur, limit);
            return R.successWithData(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return R.fail(null);
        }

    }


    /**
     * 获取用于后台博客管理的博客分页信息
     *
     * @param cur
     * @param limit
     * @return
     */
    @GetMapping(value = "/getBlogManageInfoList/{cur}/{limit}")
    //@PreAuthorize("hasAuthority('blog:get')")
    public R<Page<BlogManageVO>> getBlogManageVOInfoListRemote(@PathVariable("cur") Integer cur,
                                                               @PathVariable("limit") Integer limit) {
        try {
            Page<BlogManageVO> pageInfo = blogPOService.getBlogManageVOInfoList(cur, limit);

            return R.successWithData(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return R.fail(null);
        }

    }

    /**
     * 后台博客分类添加
     *
     * @param name
     * @return
     */
    @GetMapping(value = "/typeAdd/{name}")
    @PreAuthorize("hasAuthority('blogType:add')")
    public R<Object> blogTypeAddRemote(@PathVariable String name) {
        try {
            TypePO typePO = new TypePO();
            typePO.setName(name);

            SimpleDateFormat sdf = new SimpleDateFormat(BlogConstance.DATE_FORMAT);
            String date = sdf.format(new Date());
            typePO.setCreateTime(date);
            typePO.setUpdateTime(date);

            typePOService.save(typePO);

            return R.successWithoutData();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return R.fail(BlogConstance.SYSTEM_ERROR_BLOG_TYPE_ADD_FAILURE);
        }
    }

    /**
     * 后台博客分类删除
     *
     * @param id
     * @return
     */
    @GetMapping("/typeDelete/{id}")
    @PreAuthorize("hasAuthority('blogType:delete')")
    public R<Object> blogTypeDeleteRemote(@PathVariable Long id) {
        try {
            typePOService.removeById(id);

            return R.successWithoutData();
        } catch (Exception e) {
            log.error(e.getMessage());
            return R.fail(BlogConstance.SYSTEM_ERROR_BLOG_TYPE_DELETE_FAILURE);
        }
    }

    /**
     * 后台修改博客分类信息
     *
     * @param id
     * @param name
     * @return
     */
    @GetMapping("/typeUpdate/{id}/{name}")
    @PreAuthorize("hasAuthority('blogType:update')")
    public R<Object> blogTypeUpdateRemote(@PathVariable Long id, @PathVariable String name) {
        try {
            TypePO typePO = new TypePO();
            typePO.setName(name);
            LambdaUpdateWrapper<TypePO> wrapper = new LambdaUpdateWrapper<>();
            wrapper.eq(TypePO::getId, id);

            typePOService.update(typePO, wrapper);

            return R.successWithoutData();
        } catch (Exception e) {
            log.error(e.getMessage());
            return R.fail(BlogConstance.SYSTEM_ERROR_BLOG_TYPE_UPDATE_FAILURE);
        }
    }

    /**
     * 后台获取博客分类的分页数据
     *
     * @param cur
     * @param size
     * @return
     */
    @GetMapping("/contentTypePageInfo/{cur}/{size}")
    //@PreAuthorize("hasAuthority('blogType:get')")
    public R<Page<ContentTypeVO>> getContentTypePageInfoRemote(@PathVariable Integer cur, @PathVariable Integer size) {
        try {
            Page<ContentTypeVO> pageInfo = typePOService.getContentTypePageInfo(cur, size);

            return R.successWithData(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return R.fail(null);
        }
    }

    /**
     * 前台获取非分页的分类数据
     *
     * @return
     */
    @GetMapping("/typeContentInfoList")
    //@PreAuthorize("hasAuthority('blogType:get')")
    public R<List<ContentTypeVO>> getTypeContentInfoListRemote() {
        try {
            List<ContentTypeVO> typeManaInfoList = typePOService.getContentTypeInfoList();
            return R.successWithData(typeManaInfoList);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return R.fail(null);
        }
    }

    /**
     * 关键词查询博客分类信息
     *
     * @param keyWord
     * @param cur
     * @param size
     * @return
     */
    @GetMapping("/getTypeInfoByKeyWord/{keyWord}/{cur}/{size}")
    //@PreAuthorize("hasAuthority('blogType:get')")
    public R<Page<TypePO>> getTypeInfoListByKeyWordRemote(@PathVariable String keyWord,
                                                          @PathVariable Integer cur,
                                                          @PathVariable Integer size) {
        try {
            Page<TypePO> typePOPage = typePOService.getTypeInfoListByKeyWord(keyWord, cur, size);
            return R.successWithData(typePOPage);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return R.fail(null);
        }

    }

    /**
     * 博客标签添加
     *
     * @param name
     * @return
     */
    @GetMapping(value = "/labelAdd/{name}")
    @PreAuthorize("hasAuthority('blogLabel:add')")
    public R<Object> blogLabelAddRemote(@PathVariable String name) {
        try {
            LabelPO labelPO = new LabelPO();
            labelPO.setName(name);

            SimpleDateFormat sdf = new SimpleDateFormat(BlogConstance.DATE_FORMAT);
            String date = sdf.format(new Date());
            labelPO.setCreateTime(date);
            labelPO.setUpdateTime(date);

            labelPOService.save(labelPO);

            return R.successWithoutData();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return R.fail(BlogConstance.SYSTEM_ERROR_LABEL_TYPE_ADD_FAILURE);
        }
    }

    /**
     * 博客标签删除
     *
     * @param id
     * @return
     */
    @GetMapping("/labelDelete/{id}")
    @PreAuthorize("hasAuthority('blogLabel:delete')")
    public R<Object> blogLabelDeleteRemote(@PathVariable Long id) {
        try {
            labelPOService.removeById(id);

            return R.successWithoutData();
        } catch (Exception e) {
            log.error(e.getMessage());
            return R.fail(BlogConstance.SYSTEM_ERROR_LABEL_TYPE_DELETE_FAILURE);
        }
    }

    /**
     * 修改博客标签信息
     *
     * @param id
     * @param name
     * @return
     */
    @GetMapping("/labelUpdate/{id}/{name}")
    @PreAuthorize("hasAuthority('blogLabel:update')")
    public R<Object> blogLabelUpdateRemote(@PathVariable Long id, @PathVariable String name) {
        try {
            LabelPO labelPO = new LabelPO();
            labelPO.setName(name);
            LambdaUpdateWrapper<LabelPO> wrapper = new LambdaUpdateWrapper<>();
            wrapper.eq(LabelPO::getId, id);

            labelPOService.update(labelPO, wrapper);

            return R.successWithoutData();
        } catch (Exception e) {
            log.error(e.getMessage());
            return R.fail(BlogConstance.SYSTEM_ERROR_BLOG_TYPE_DELETE_FAILURE);
        }
    }

    /**
     * 获取前后台标签管理的分页数据
     *
     * @param cur
     * @param size
     * @return
     */
    @GetMapping("/labelContentPageInfo/{cur}/{size}")
    //@PreAuthorize("hasAuthority('blogLabel:get')")
    public R<Page<ContentLabelVO>> getLabelInfoListRemote(@PathVariable Integer cur, @PathVariable Integer size) {
        try {
            Page<ContentLabelVO> labelManPageInfo = labelPOService.getLabelManPageInfo(cur, size);
            return R.successWithData(labelManPageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return R.fail(null);
        }
    }

    /**
     * 前台获取分类简要信息
     *
     * @return
     */
    @GetMapping("/labelContentInfoList")
    //@PreAuthorize("hasAuthority('blogLabel:get')")
    public R<List<ContentLabelVO>> getLabelInfoListWithoutPage() {
        try {
            List<ContentLabelVO> labelContentInfoList = labelPOService.getLabelContentInfoList();
            return R.successWithData(labelContentInfoList);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return R.fail(null);
        }
    }

    /**
     * 关键词查询博客标签信息
     *
     * @param keyWord
     * @param cur
     * @param size
     * @return
     */
    @GetMapping("/getLabelInfoByKeyWord/{keyWord}/{cur}/{size}")
    //@PreAuthorize("hasAuthority('blogLabel:get')")
    public R<Page<LabelPO>> getLabelInfoListByKeyWordRemote(@PathVariable String keyWord,
                                                            @PathVariable Integer cur,
                                                            @PathVariable Integer size) {
        try {
            Page<LabelPO> labelPOPage = labelPOService.getLabelInfoListByKeyWord(keyWord, cur, size);
            return R.successWithData(labelPOPage);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return R.fail(null);
        }

    }

    /**
     * 获取用于后台博客管理的博客分类视图对象
     *
     * @return
     */
    @GetMapping("/getBlogTypeInfoList")
    //@PreAuthorize("hasAuthority('blogType:get')")
    public R<List<BlogManTypeVO>> getBlogTypeInfoListRemote() {
        try {
            List<TypePO> list = typePOService.list();

            List<BlogManTypeVO> typeVOList = new ArrayList<>();

            for (TypePO typePO : list) {
                BlogManTypeVO typeVO = new BlogManTypeVO();
                BeanUtils.copyProperties(typePO, typeVO);

                typeVOList.add(typeVO);
            }

            return R.successWithData(typeVOList);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return R.fail(null);
        }

    }

    /**
     * 获取用于后台博客管理的博客标签视图对象
     *
     * @return
     */
    @GetMapping("/getBlogLabelInfoList")
    //@PreAuthorize("hasAuthority('blogLabel:get')")
    public R<List<BlogManLabelVO>> getBlogLabelInfoListRemote() {
        try {
            List<LabelPO> list = labelPOService.list();

            List<BlogManLabelVO> labelVOList = new ArrayList<>();

            for (LabelPO labelPO : list) {
                BlogManLabelVO labelVO = new BlogManLabelVO();
                BeanUtils.copyProperties(labelPO, labelVO);

                labelVOList.add(labelVO);
            }

            return R.successWithData(labelVOList);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return R.fail(null);
        }

    }


    /**
     * 前台服务
     */

    /**
     * 前台根据分类id获取博客信息
     * @param cur
     * @param limit
     * @param typeId
     * @return
     */
    @GetMapping("/getBlogPageInfoByTypeId/{cur}/{limit}")
    //@PreAuthorize("hasAuthority('blog:get')")
    public R<Page<BlogVO>> getBlogVOPageInfoByTypeIdRemote(@PathVariable Integer cur,
                                                           @PathVariable Integer limit,
                                                           @Param("typeId") Long typeId) {
        try {
            Page<BlogVO> blogPageInfoByTypeId = null;
            if(typeId == null) {
                blogPageInfoByTypeId = blogPOService.getAllBlogPageInfo(cur, limit);
            } else {
                blogPageInfoByTypeId = blogPOService.getBlogPageInfoByTypeId(cur, limit, typeId);
            }
            return R.successWithData(blogPageInfoByTypeId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return R.fail(null);
        }
    }

}
