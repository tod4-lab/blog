package com.hikaru.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hikaru.blog.mapper.CommentPOMapper;
import com.hikaru.blog.service.CommentPOService;
import com.hikaru.entity.po.CommentPO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author admin
* @description 针对表【t_comment】的数据库操作Service实现
* @createDate 2022-11-06 11:10:28
*/
@Service
@Transactional
public class CommentPOServiceImpl extends ServiceImpl<CommentPOMapper, CommentPO>
    implements CommentPOService{

}




