package com.qin.controller;

import com.qin.common.ServerResponse;
import com.qin.service.IPostService;
import com.qin.util.DateUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/portal/post/")
public class PostController {

    @Resource
    IPostService postService;

    @RequestMapping("retrieve")
    public ServerResponse retrieve(Integer categoryId,
                               @RequestParam(value = "time",defaultValue = "2020-03-29") String time,
                               String address,
                               Integer LoF,
                               String keyword,
                               Integer userId,
                               @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                               @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                               @RequestParam(value = "orderBy",defaultValue = "time_desc") String orderBy){
        return postService.retrieve(categoryId, DateUtils.string2Date(time),address,LoF,keyword,userId,pageNum,pageSize,orderBy);
    }
}
