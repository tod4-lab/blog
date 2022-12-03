package com.hikaru.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hikaru.common.constance.AuthConstance;
import com.hikaru.common.constance.ResultCode;
import com.hikaru.common.result.R;
import com.hikaru.common.utils.OSSUploadUtil;
import com.hikaru.entity.SysRole;
import com.hikaru.entity.SysUser;
import com.hikaru.serviceUtil.exception.AuthException;
import com.hikaru.system.config.OssProperties;
import com.hikaru.system.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/sys/user")
@Slf4j
public class SysUserController {
    @Autowired
    SysUserService userService;

    @Autowired
    OssProperties ossProperties;

    /**
     * 获取用户分页信息
     * @param cur
     * @param limit
     * @return
     */
    @GetMapping("/page/{cur}/{limit}")
    @PreAuthorize("hasAuthority('user:get')")
    public R<Page<SysUser>> getUserPageHandle(@PathVariable("cur") Integer cur,
                                        @PathVariable("limit") Integer limit) {
        Page<SysUser> page = new Page<>(cur, limit);
        userService.page(page);

        return R.successWithData(page);
    }

    /**
     * 根据关键词查询分页信息
     * @param keyWord
     * @param limit
     * @param cur
     * @return
     */
    @GetMapping("/keyWord/{keyWord}/{cur}/{limit}")
    @PreAuthorize("hasAuthority('user:get')")
    public R<Page<SysUser>> getUserPageByKeyWordHandle(@PathVariable("keyWord") String keyWord,
                                                 @PathVariable("limit") Integer limit,
                                                 @PathVariable("cur") Integer cur) {
        Page<SysUser> page = new Page<>(cur, limit);
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(!StringUtils.isBlank(keyWord), SysUser::getUsername, keyWord)
                .or()
                .like(!StringUtils.isBlank(keyWord), SysUser::getName, keyWord);
        userService.page(page, queryWrapper);

        return R.successWithData(page);
    }

    /**
     * 新增用户
     * @param user
     * @return
     */
    @PostMapping()
    @PreAuthorize("hasAuthority('user:add')")
    public R<Object> addUserHandle(@RequestBody SysUser user) {
        try {
            userService.save(user);
        } catch (DuplicateKeyException e) {
            throw new AuthException(ResultCode.ERROR, AuthConstance.MESSAGE_USER_NAME_ALREADY_EXISTS);
        }
        return R.successWithoutData();
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('user:delete')")
    public R<Object> deleteUserByIdHandle(@PathVariable Long id) {
        userService.deleteRoleInfoByUserId(id);
        boolean b = userService.removeById(id);
        if(!b) {
            throw new AuthException();
        }
        return R.successWithoutData();
    }

    /**
     * 修改用户
     * @param user
     * @return
     */
    @PutMapping()
    @PreAuthorize("hasAuthority('user:update')")
    public R<Object> updateUserHandle(@RequestBody SysUser user) {
        boolean b = userService.updateById(user);
        if(!b) {
            throw new AuthException();
        }
        return R.successWithoutData();
    }

    /**
     * 获取用户角色
     * @param userId
     * @return
     */
    @GetMapping("/role/{userId}")
    @PreAuthorize("hasAuthority('user:get')")
    public R<List<SysRole>> getRoleInfoByUserId(@PathVariable Long userId) {
        List<SysRole> roleInfoListByUserId = userService.getRoleInfoListByUserId(userId);
        return R.successWithData(roleInfoListByUserId);
    }

    /**
     * 上传头像
     * @param multipartFile
     * @return
     */
    @RequestMapping("/uploadAvatar")
    @PreAuthorize("hasAuthority('user:upload')")
    public R<String> uploadAvatarHandle(@RequestPart("file") MultipartFile multipartFile) {
        String originalFilename = multipartFile.getOriginalFilename();
        if(StringUtils.isBlank(originalFilename)) {
            return R.fail(ResultCode.ERROR, AuthConstance.MESSAGE_PIC_UPLOAD_FAILURE);
        }
        try {
            InputStream inputStream = multipartFile.getInputStream();

            return OSSUploadUtil.uploadFileToOss(ossProperties.getEndPoint(),
                    ossProperties.getAccessKeyId(),
                    ossProperties.getAccessKeySecret(),
                    ossProperties.getBucketName(),
                    ossProperties.getBucketDomain(),
                    inputStream,
                    originalFilename);
        } catch (IOException e) {
            e.printStackTrace();
            return R.fail(ResultCode.ERROR, AuthConstance.MESSAGE_PIC_UPLOAD_FAILURE);
        }
    }
}
