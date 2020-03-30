package com.qin.service;

import com.qin.common.ServerResponse;

public interface ICategoryService {
    /**
     * 查询一级类别
     * */
    public ServerResponse findSubCategory(Integer categoryID);

    /**
     * 查询categoryID下的所有子类别
     * */
    public ServerResponse findChildCategory(Integer categoryID);

}
