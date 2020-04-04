package com.qin.controller;

import com.qin.common.Const;
import com.qin.common.ResponseCode;
import com.qin.common.ServerResponse;
import com.qin.exception.LafException;
import com.qin.pojo.vo.UserVO;
import com.qin.service.impl.OrderService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/portal/order/")
public class OrderController {

    @Resource
    OrderService orderService;

    @RequestMapping("create/{postID}")
    public ServerResponse createOrder(@PathVariable("postID") Integer postID, HttpSession session) {

        if (postID != null)
            ServerResponse.createServerResponseByFail(ResponseCode.PARAMETER_NOT_EMPTY.getCode(), ResponseCode.PARAMETER_NOT_EMPTY.getMsg());

        UserVO userVO = (UserVO) session.getAttribute(Const.CURRENT_USER);

        try {
            return orderService.createOrder(postID, userVO.getId());
        } catch (LafException e) {
            return ServerResponse.createServerResponseByFail(-1, e.getMsg());
        }
    }
}
