package com.hikaru.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hikaru.entity.po.TypePO;
import com.hikaru.entity.vo.ContentTypeVO;

import java.util.List;

/**
* @author admin
* @description 针对表【t_type】的数据库操作Service
* @createDate 2022-11-03 20:40:29
*/
public interface TypePOService extends IService<TypePO> {
    Page<TypePO> getTypeInfoListByKeyWord(String keyWord, Integer cur, Integer size);
    TypePO getTypePOInfoByBlogId(Long blogId);
    Page<ContentTypeVO> getContentTypePageInfo(int cur, int limit);
    List<ContentTypeVO> getContentTypeInfoList();
}
