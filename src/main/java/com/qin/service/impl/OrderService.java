package com.qin.service.impl;

import com.qin.common.OrderStatusEnum;
import com.qin.common.ResponseCode;
import com.qin.common.ServerResponse;
import com.qin.dao.CarouselMapper;
import com.qin.dao.PayInfoMapper;
import com.qin.exception.LafException;
import com.qin.pojo.Carousel;
import com.qin.pojo.PayInfo;
import com.qin.pojo.vo.PayInfoVO;
import com.qin.pojo.vo.PostVO;
import com.qin.service.IPostService;
import com.qin.util.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class OrderService implements IOrderService {

    @Resource
    IPostService postService;
    @Resource
    PayInfoMapper payInfoMapper;
    @Resource
    CarouselMapper carouselMapper;

    @Override
    public ServerResponse createOrder(Integer postID,Integer userID) {

        //根据postID查询post信息
        PostVO postVO=postService.selectByPrimaryKey(postID);
        if(postVO==null)
            throw new LafException("帖子不存在");

        //将订单插入数据库
        PayInfo payInfo=new PayInfo();
        payInfo.setOrderNo(generateOrderNo());
        payInfo.setUserId(userID);
        payInfo.setPostId(postID);
        payInfo.setCreateTime(new Date(System.currentTimeMillis()));
//        payInfo.setPayPlatform();
        payInfo.setPlatformStatus(OrderStatusEnum.ORDER_NO_PAYED.getCode());

        int countPayInfo=payInfoMapper.insert(payInfo);
        if(countPayInfo==0)
            return ServerResponse.createServerResponseByFail(ResponseCode.ORDER_CREATE_FAIL.getCode(),ResponseCode.ORDER_CREATE_FAIL.getMsg());

        //将帖子插入轮播表
        Carousel carousel=new Carousel();
        carousel.setPostId(postID);
        carousel.setOrder(1);
        carousel.setTime( new Date(System.currentTimeMillis()));
        int countCarousel=carouselMapper.insert(carousel);
        if(countCarousel==0)
            throw new LafException("轮播设置失败");

        PayInfoVO payInfoVO=getPayInfoVO(payInfo);
        if(payInfoVO==null)
            ServerResponse.createServerResponseByFail(ResponseCode.ORDER_CREATE_FAIL.getCode(),ResponseCode.ORDER_CREATE_FAIL.getMsg());

        return ServerResponse.createServerResponseBySuccess(payInfoVO);
    }

    //生成订单号(需迭代)
    public long generateOrderNo(){
        return System.currentTimeMillis();
    }

    private PayInfoVO getPayInfoVO(PayInfo payInfo){
        PayInfoVO payInfoVO=new PayInfoVO();
//        payInfoVO.setId(payInfo.getId());
        payInfoVO.setUserId(payInfo.getUserId());
        payInfoVO.setPostId(payInfo.getPostId());
        payInfoVO.setPlatformStatus(payInfo.getPlatformStatus());
        payInfoVO.setPlatformNumber(payInfo.getPlatformNumber());
        payInfoVO.setPayPlatform(payInfo.getPayPlatform());
        payInfoVO.setOrderNo(payInfo.getOrderNo());
        payInfoVO.setCreateTime(DateUtils.date2String(payInfo.getCreateTime()));

        return payInfoVO;
    }
}
