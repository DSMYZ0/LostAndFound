package com.qin.controller;

import com.qin.common.ServerResponse;
import com.qin.service.ICategoryService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("portal/category/")
public class CategoryController {

    @Resource
    ICategoryService categoryService;

    @RequestMapping("{categoryID}")
    //@PathVariable能获得URL中的动态参数
    public ServerResponse findSubCategory(@PathVariable("categoryID") Integer categoryID){
        return categoryService.findSubCategory(categoryID);
    }

    @RequestMapping("child/{categoryID}")
    //@PathVariable能获得URL中的动态参数
    public ServerResponse findChildCategory(@PathVariable("categoryID") Integer categoryID){
        return categoryService.findChildCategory(categoryID);
    }
}
