package com.qin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qin.common.ResponseCode;
import com.qin.common.ServerResponse;
import com.qin.dao.PostMapper;
import com.qin.pojo.Post;
import com.qin.pojo.vo.PostVO;
import com.qin.service.IPostService;
import com.qin.util.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostService implements IPostService {

    @Resource
    PostMapper postMapper;

    @Override
    public ServerResponse retrieve(Integer categoryId, Date time, String address,Integer LoF,String keyword,Integer pageNum,Integer pageSize,String orderBy) {

        PageHelper.startPage(pageNum,pageSize);
        if(StringUtils.isNotBlank(orderBy)){
            String[] orders=orderBy.split("_");
            if(orders.length!=2)
                ServerResponse.createServerResponseByFail(ResponseCode.ILLEGAL_PARAM.getCode(),ResponseCode.ILLEGAL_PARAM.getMsg());
            PageHelper.orderBy(orders[0]+" "+orders[1]);
        }


        List<Post> postList=postMapper.retrieve(categoryId,time,address,LoF,keyword);
        List<PostVO> postVOList=new ArrayList<>();
        for(Post post:postList){
            PostVO postVO=post2PostVO(post);
            postVOList.add(postVO);
        }

        PageInfo pageInfo=new PageInfo(postList);
        pageInfo.setList(postVOList);

        return ServerResponse.createServerResponseBySuccess(pageInfo);
    }


    public PostVO post2PostVO(Post post){
        PostVO postVO=new PostVO();
        postVO.setAddress(post.getAddress());
        postVO.setCategory_id(post.getCategoryId());
        postVO.setDetail(post.getDetail());
        postVO.setId(post.getId());
        postVO.setLoF(post.getLoF());
        postVO.setName(post.getName());
        postVO.setTime(DateUtils.date2String(post.getTime()));
        postVO.setPicture(post.getPicture());
        postVO.setPicture_url(post.getPictureUrl());

        return postVO;
    }
}
