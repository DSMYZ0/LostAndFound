package com.qin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qin.common.ResponseCode;
import com.qin.common.ServerResponse;
import com.qin.dao.CarouselMapper;
import com.qin.dao.PostMapper;
import com.qin.pojo.Carousel;
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
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class PostService implements IPostService {

    @Resource
    PostMapper postMapper;
    @Resource
    CarouselMapper carouselMapper;

    @Override
    public ServerResponse retrieve(Integer categoryId, Date time, String address,Integer LoF,String keyword,Integer userId,Integer pageNum,Integer pageSize,String orderBy) {

        PageHelper.startPage(pageNum,pageSize);
        if(StringUtils.isNotBlank(orderBy)){
            String[] orders=orderBy.split("_");
            if(orders.length!=2)
                ServerResponse.createServerResponseByFail(ResponseCode.ILLEGAL_PARAM.getCode(),ResponseCode.ILLEGAL_PARAM.getMsg());
            PageHelper.orderBy(orders[0]+" "+orders[1]);
        }


        List<Post> postList=postMapper.retrieve(categoryId,time,address,LoF,keyword,userId);
        List<PostVO> postVOList=new ArrayList<>();
        for(Post post:postList){
            PostVO postVO=post2PostVO(post);
            postVOList.add(postVO);
        }

        PageInfo pageInfo=new PageInfo(postList);
        pageInfo.setList(postVOList);

        return ServerResponse.createServerResponseBySuccess(pageInfo);
    }

    @Override
    public Post selectByPrimaryKey(Integer postID) {
        return postMapper.selectByPrimaryKey(postID);
    }

    @Override
    public ServerResponse getCarousel() {

        List<Carousel> carouselList=carouselMapper.selectAll();
        if(carouselList==null)
            ServerResponse.createServerResponseByFail(ResponseCode.CAROUSEL_EMPTY.getCode(),ResponseCode.CAROUSEL_EMPTY.getMsg());

        //ArrayList的父类AbstarctList中有一个域modCount,遍历时修改集合会造成expectedModCount!=modCount
        List<PostVO> postVOList=new CopyOnWriteArrayList<>();
        for(Carousel carousel:carouselList){
            //检查有效期
//            long createTime=carousel.getTime().getTime();
//            long limitedTime=(System.currentTimeMillis()-createTime)/(1000*60*60);
//            if(limitedTime>24){
//                carouselMapper.deleteByPrimaryKey(carousel.getId());
//                carouselList.remove(carousel);
//                continue;
//            }

            Post post=postMapper.selectByPrimaryKey(carousel.getPostId());
            if(post!=null)
                postVOList.add(post2PostVO(post));
        }

        return ServerResponse.createServerResponseBySuccess(postVOList);
    }

    @Override
    public ServerResponse addPost(Post post) {
        if(post==null)
            return ServerResponse.createServerResponseByFail(ResponseCode.POST_FAILED.getCode(),ResponseCode.POST_FAILED.getMsg());
        int count=postMapper.insert(post);

        if(count==1)
            return ServerResponse.createServerResponseBySuccess(ResponseCode.POST_SUCCESSED.getCode(),ResponseCode.POST_SUCCESSED.getMsg());

        return ServerResponse.createServerResponseByFail(ResponseCode.POST_FAILED.getCode(),ResponseCode.POST_FAILED.getMsg());
    }

    @Override
    public ServerResponse updatePost(Post post) {

        if(post==null)
            return ServerResponse.createServerResponseByFail(ResponseCode.UPDATE_FAILED.getCode(),ResponseCode.UPDATE_FAILED.getMsg());

        int count=postMapper.updateByPrimaryKey(post);

        if(count==1) {
            Post post1=postMapper.selectByPrimaryKey(post.getId());
            if(post1!=null)
                return ServerResponse.createServerResponseBySuccess(post2PostVO(post1));
        }

        return ServerResponse.createServerResponseByFail(ResponseCode.UPDATE_FAILED.getCode(),ResponseCode.UPDATE_FAILED.getMsg());

    }

    @Override
    public ServerResponse deletePost(Integer id) {
        Post post=postMapper.selectByPrimaryKey(id);
        if(post==null)
            return ServerResponse.createServerResponseByFail(ResponseCode.POST_NOT_EXIST.getCode(),ResponseCode.POST_NOT_EXIST.getMsg());

        int count=postMapper.deleteByPrimaryKey(id);
        if(count==1)
            return ServerResponse.createServerResponseBySuccess();
        return ServerResponse.createServerResponseByFail(ResponseCode.POST_DELETE_FAILED.getCode(),ResponseCode.POST_DELETE_FAILED.getMsg());
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
        postVO.setUser_id(post.getUserId());

        return postVO;
    }
}
