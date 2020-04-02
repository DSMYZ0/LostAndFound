package com.qin.service.impl;

import com.qin.common.ServerResponse;

public interface IOrderService {

    /**
     * 创建订单
     * */
    public ServerResponse createOrder(Integer postID,Integer userID);
}
