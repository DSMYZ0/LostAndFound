package com.qin.service;

import com.qin.common.ServerResponse;

import java.util.Map;

public interface IPayService {

    public ServerResponse pay(Long orderNo);

    public String callback(Map<String,String> map);
}
