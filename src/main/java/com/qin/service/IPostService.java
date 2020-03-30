package com.qin.service;

import com.qin.common.ServerResponse;

import java.util.Date;

public interface IPostService {

    /**
     * 检索发布信息
     * */
    public ServerResponse retrieve(Integer categoryId, Date time, String address,Integer LoF,String keyword,Integer pageNum,Integer pageSize,String orderBy);

}
