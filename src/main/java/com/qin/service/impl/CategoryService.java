package com.qin.service.impl;

import com.qin.common.ResponseCode;
import com.qin.common.ServerResponse;
import com.qin.dao.CategoryMapper;
import com.qin.pojo.Category;
import com.qin.service.ICategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CategoryService implements ICategoryService {

    @Resource
    CategoryMapper categoryMapper;

    @Override
    public ServerResponse findSubCategory(Integer categoryID) {
        if(categoryID==null)
            return ServerResponse.createServerResponseByFail(ResponseCode.PARAMETER_NOT_EMPTY.getCode(),ResponseCode.PARAMETER_NOT_EMPTY.getMsg());
        List<Category> categoryList=categoryMapper.findSubCategoryByID(categoryID);
        return ServerResponse.createServerResponseBySuccess(categoryList);
    }

    @Override
    public ServerResponse findChildCategory(Integer categoryID) {
        if(categoryID==null)
            return ServerResponse.createServerResponseByFail(ResponseCode.PARAMETER_NOT_EMPTY.getCode(),ResponseCode.PARAMETER_NOT_EMPTY.getMsg());

        Set<Category> set=new HashSet<>();
        Set<Category> categorySet=findChildCategory(set,categoryID);
        return ServerResponse.createServerResponseBySuccess(categorySet);
    }

    private Set<Category> findChildCategory(Set<Category> categorySet,Integer categoryID){

        //根据categoryID查询
        Category category=categoryMapper.selectByPrimaryKey(categoryID);
        if(category!=null)
            categorySet.add(category);

        //查询category下所有子类别
        List<Category> categoryList=categoryMapper.findSubCategoryByID(categoryID);
        for(Category category1:categoryList){
            findChildCategory(categorySet,category1.getId());
        }
        return categorySet;
    }
}
