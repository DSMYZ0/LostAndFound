package com.qin.service.impl;

import com.qin.common.Const;
import com.qin.common.ResponseCode;
import com.qin.common.ServerResponse;
import com.qin.dao.UserInfoMapper;
import com.qin.pojo.UserInfo;
import com.qin.pojo.vo.UserVO;
import com.qin.service.IUserService;
import com.qin.util.MD5Utils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public ServerResponse login(String username, String password) {

        //用户名非空校验
        if (StringUtils.isBlank(username))
            return ServerResponse.createServerResponseByFail(ResponseCode.USERNAME_NOT_EMPTY.getCode(), ResponseCode.USERNAME_NOT_EMPTY.getMsg());
        //密码非空校验
        if (StringUtils.isBlank(password))
            return ServerResponse.createServerResponseByFail(ResponseCode.PASSWORD_NOT_EMPTY.getCode(), ResponseCode.PASSWORD_NOT_EMPTY.getMsg());
        //判断用户名是否存在
        Integer count=userInfoMapper.findByUsername(username);
        if(count==0)
            return ServerResponse.createServerResponseByFail(ResponseCode.USERNAME_NOT_EXIST.getCode(),ResponseCode.USERNAME_NOT_EXIST.getMsg());
        //密码校验
        UserInfo userInfo=userInfoMapper.findByUsernameAndPassword(username,MD5Utils.getMD5Code(password));
        if(userInfo==null)
            return  ServerResponse.createServerResponseByFail(ResponseCode.PASSWORD_ERROR.getCode(),ResponseCode.PASSWORD_ERROR.getMsg());
        //登录成功 返回用户信息
        return ServerResponse.createServerResponseBySuccess(convert(userInfo));
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public ServerResponse register(UserInfo userInfo) {

        if(userInfo==null)
            return ServerResponse.createServerResponseByFail(ResponseCode.PARAMETER_NOT_EMPTY.getCode(),ResponseCode.PARAMETER_NOT_EMPTY.getMsg());

        String username=userInfo.getUsername();
        String password=userInfo.getPassword();
        String contact=userInfo.getContact();

        //用户名非空校验
        if (StringUtils.isBlank(username))
            return ServerResponse.createServerResponseByFail(ResponseCode.USERNAME_NOT_EMPTY.getCode(), ResponseCode.USERNAME_NOT_EMPTY.getMsg());
        //密码非空校验
        if (StringUtils.isBlank(password))
            return ServerResponse.createServerResponseByFail(ResponseCode.PASSWORD_NOT_EMPTY.getCode(), ResponseCode.PASSWORD_NOT_EMPTY.getMsg());
        //判断用户名是否存在
        Integer count=userInfoMapper.findByUsername(username);
        if(count>0)
            return ServerResponse.createServerResponseByFail(ResponseCode.USERNAME_IS_EXIST.getCode(),ResponseCode.USERNAME_IS_EXIST.getMsg());
        //密码加密设置权限
        userInfo.setPassword(MD5Utils.getMD5Code(userInfo.getPassword()));
        userInfo.setRole(Const.NORMAL_USER);
        //注册
        Integer result=userInfoMapper.insert(userInfo);
        if(result==0)   //注册失败
            return ServerResponse.createServerResponseByFail(ResponseCode.REGISTER_FAIL.getCode(),ResponseCode.REGISTER_FAIL.getMsg());

        return ServerResponse.createServerResponseBySuccess();
    }

    private UserVO convert(UserInfo userInfo){
        UserVO userVO=new UserVO();
        userVO.setId(userInfo.getId());
        userVO.setContact(userInfo.getContact());
        userVO.setProfile(userInfo.getProfile());
        userVO.setProfileUrl(userInfo.getProfileUrl());
        userVO.setRole(userInfo.getRole());
        return userVO;
    }

}
