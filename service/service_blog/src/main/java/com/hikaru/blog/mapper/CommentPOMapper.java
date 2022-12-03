package com.hikaru.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hikaru.entity.po.CommentPO;
import org.apache.ibatis.annotations.Mapper;

/**
* @author admin
* @description 针对表【t_comment】的数据库操作Mapper
* @createDate 2022-11-06 11:10:28
* @Entity com.hikaru.blog.entity.CommentPO
*/
@Mapper
public interface CommentPOMapper extends BaseMapper<CommentPO> {

}




