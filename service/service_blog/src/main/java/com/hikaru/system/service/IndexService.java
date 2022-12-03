package com.hikaru.system.service;

import com.hikaru.entity.SysMenu;
import com.hikaru.entity.vo.LoginVO;

import java.util.List;
import java.util.Map;

public interface IndexService {
    Map<String, String> login(LoginVO loginVO);
    void logOut(String token);
    List<String> getAuthorityListByUserId(Long userId);
    List<SysMenu> getMenuInfoListByPermsList(List<String> permsList);
}
