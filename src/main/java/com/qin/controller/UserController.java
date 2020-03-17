package com.qin.controller;

import com.qin.common.Const;
import com.qin.common.ResponseCode;
import com.qin.common.ServerResponse;
import com.qin.pojo.UserInfo;
import com.qin.pojo.vo.UserVO;
import com.qin.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/portal/")
public class UserController {

    @Autowired
    IUserService userService;

    @RequestMapping("user/login")
    public ServerResponse<UserInfo> login(String username, String password, HttpSession session){

        ServerResponse serverResponse=userService.login(username,password);
        if(serverResponse.isSuccess())
            session.setAttribute(Const.CURRENT_USER,serverResponse.getData());
        return serverResponse;
    }

    @RequestMapping("user/register")
    public ServerResponse register(UserInfo userInfo){
        return userService.register(userInfo);
    }

    @RequestMapping("user/update")
    public ServerResponse update(UserInfo userInfo,HttpSession session){

        UserVO userVO=(UserVO)session.getAttribute(Const.CURRENT_USER);

        if(userVO==null)
            return ServerResponse.createServerResponseByFail(ResponseCode.NOT_LOGIN.getCode(),ResponseCode.NOT_LOGIN.getMsg());
        if(userInfo==null)
            return ServerResponse.createServerResponseByFail(ResponseCode.PARAMETER_NOT_EMPTY.getCode(),ResponseCode.PARAMETER_NOT_EMPTY.getMsg());

        userInfo.setId(userVO.getId());
        ServerResponse serverResponse = userService.updateUserInfo(userInfo);
        //更新session
        if(serverResponse.isSuccess())
            session.setAttribute(Const.CURRENT_USER,serverResponse.getData());
        return serverResponse;
    }
}
