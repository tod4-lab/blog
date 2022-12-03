package com.hikaru.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hikaru.blog.mapper.TypePOMapper;
import com.hikaru.blog.service.TypePOService;
import com.hikaru.entity.po.TypePO;
import com.hikaru.entity.vo.ContentTypeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author admin
* @description 针对表【t_type】的数据库操作Service实现
* @createDate 2022-11-03 20:40:29
*/
@Service
@Transactional
public class TypePOServiceImpl extends ServiceImpl<TypePOMapper, TypePO>
    implements TypePOService{

    @Autowired
    TypePOMapper typePOMapper;

    @Override
    public Page<TypePO> getTypeInfoListByKeyWord(String keyWord, Integer cur, Integer size) {
        Page<TypePO> page = new Page<>(cur, size);
        LambdaQueryWrapper<TypePO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(TypePO::getName, keyWord);

        return typePOMapper.selectPage(page, queryWrapper);
    }

    @Override
    public TypePO getTypePOInfoByBlogId(Long blogId) {
        return typePOMapper.selectTypePOInfoByBlogId(blogId);
    }

    @Override
    public Page<ContentTypeVO> getContentTypePageInfo(int cur, int limit) {
        Page<ContentTypeVO> page = new Page<>(cur, limit);
        return typePOMapper.selectContentTypePage(page);
    }

    @Override
    public List<ContentTypeVO> getContentTypeInfoList() {
        return typePOMapper.selectContentTypeInfo();
    }

}




