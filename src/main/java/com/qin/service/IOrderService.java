package com.qin.service;

import com.qin.common.ServerResponse;

import java.util.Date;

public interface IOrderService {

    /**
     * 创建订单
     * */
    ServerResponse createOrder(Integer postID, Integer userID);

    /**
     * 更新订单
     * */
    ServerResponse updateOrder(Long orderNo,Integer status, Date payedTime);
}
