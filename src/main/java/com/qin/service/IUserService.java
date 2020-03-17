package com.qin.service;

import com.qin.common.ServerResponse;
import com.qin.pojo.UserInfo;
import org.springframework.stereotype.Component;

public interface IUserService {

    /**
    * 登录
    * */
    public ServerResponse login(String username,String password);
    /**
     * 注册
     * */
    public ServerResponse register(UserInfo userInfo);
    /**
     * 修改用户信息
     * */
    public ServerResponse updateUserInfo(UserInfo userInfo);
}
