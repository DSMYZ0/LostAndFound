package com.qin.service;

import com.qin.common.ServerResponse;
import com.qin.pojo.Post;
import com.qin.pojo.vo.PostVO;

import java.util.Date;

public interface IPostService {

    /**
     * 检索发布信息
     * */
    public ServerResponse retrieve(Integer categoryId, Date time, String address,Integer LoF,String keyword,Integer userId,Integer pageNum,Integer pageSize,String orderBy);

    /**
     * 根据postID查询post信息
     * */
    public Post selectByPrimaryKey(Integer postID);

    /**
     * 获取轮播图
     * */
    ServerResponse getCarousel();

    /**
     * 发布信息
     * */
    ServerResponse addPost(Post post);

    /**
     * 修改发布信息
     * */
    ServerResponse updatePost(Post post);

    /**
     * 删除发布信息
     * */
    ServerResponse deletePost(Integer id);
}
