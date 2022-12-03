package com.hikaru.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hikaru.entity.po.LabelPO;
import com.hikaru.entity.vo.ContentLabelVO;

import java.util.List;

/**
* @author admin
* @description 针对表【t_label】的数据库操作Service
* @createDate 2022-11-03 20:40:19
*/
public interface LabelPOService extends IService<LabelPO> {
    Page<LabelPO> getLabelInfoListByKeyWord(String keyWord, Integer cur, Integer size);
    Page<ContentLabelVO> getLabelManPageInfo(int cur, int limit);
    List<ContentLabelVO> getLabelContentInfoList();
}
