package com.hikaru.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hikaru.blog.mapper.LabelPOMapper;
import com.hikaru.blog.service.LabelPOService;
import com.hikaru.entity.po.LabelPO;
import com.hikaru.entity.vo.ContentLabelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author admin
* @description 针对表【t_label】的数据库操作Service实现
* @createDate 2022-11-03 20:40:19
*/
@Service
@Transactional
public class LabelPOServiceImpl extends ServiceImpl<LabelPOMapper, LabelPO> implements LabelPOService{

    @Autowired
    LabelPOMapper labelPOMapper;

    @Override
    public Page<LabelPO> getLabelInfoListByKeyWord(String keyWord, Integer cur, Integer size) {
        Page<LabelPO> page = new Page<>(cur, size);
        LambdaQueryWrapper<LabelPO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(LabelPO::getName, keyWord);

        return labelPOMapper.selectPage(page, queryWrapper);
    }

    @Override
    public Page<ContentLabelVO> getLabelManPageInfo(int cur, int limit) {
        Page<ContentLabelVO> page = new Page<>(cur, limit);

        return labelPOMapper.selectLabelContentPage(page);
    }

    @Override
    public List<ContentLabelVO> getLabelContentInfoList() {
        return labelPOMapper.selectLabelContentInfo();
    }
}




