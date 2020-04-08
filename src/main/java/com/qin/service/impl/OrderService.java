package com.qin.service.impl;

import com.qin.common.OrderStatusEnum;
import com.qin.common.ResponseCode;
import com.qin.common.ServerResponse;
import com.qin.dao.CarouselMapper;
import com.qin.dao.PayInfoMapper;
import com.qin.exception.LafException;
import com.qin.pojo.Carousel;
import com.qin.pojo.PayInfo;
import com.qin.pojo.Post;
import com.qin.pojo.vo.PayInfoVO;
import com.qin.service.IOrderService;
import com.qin.service.IPostService;
import com.qin.util.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class OrderService implements IOrderService {

    @Resource
    IPostService postService;
    @Resource
    PayInfoMapper payInfoMapper;

    @Transactional(rollbackFor = {LafException.class})
    @Override
    public ServerResponse createOrder(Integer postID,Integer userID) {

        //根据postID查询post信息
        Post post=postService.selectByPrimaryKey(postID);
        if(post==null)
            throw new LafException("帖子不存在");

        //将订单插入数据库
        PayInfo payInfo=new PayInfo();
        payInfo.setOrderNo(generateOrderNo());
        payInfo.setUserId(userID);
        payInfo.setPostId(postID);
        payInfo.setCreateTime(new Date());
        payInfo.setPayPlatform(1);
        payInfo.setPlatformStatus(OrderStatusEnum.ORDER_NO_PAYED.getCode());

        int countPayInfo=payInfoMapper.insert(payInfo);
        if(countPayInfo==0)
            return ServerResponse.createServerResponseByFail(ResponseCode.ORDER_CREATE_FAIL.getCode(),ResponseCode.ORDER_CREATE_FAIL.getMsg());


        //查寻订单
        PayInfo payInfo1=payInfoMapper.findPayInfoByOrderNo(payInfo.getOrderNo());
        if(payInfo1==null)
            ServerResponse.createServerResponseByFail(ResponseCode.ORDER_CREATE_FAIL.getCode(),ResponseCode.ORDER_CREATE_FAIL.getMsg());

        PayInfoVO payInfoVO=getPayInfoVO(payInfo1);

        return ServerResponse.createServerResponseBySuccess(payInfoVO);
    }

    @Override
    public ServerResponse updateOrder(Long orderNo,Integer platformStatus, Date payedTime) {

        if(0==payInfoMapper.updateOrderByPayment(orderNo,platformStatus,payedTime))
            return ServerResponse.createServerResponseByFail(ResponseCode.ORDER_UPDATE_FAIL.getCode(),ResponseCode.ORDER_UPDATE_FAIL.getMsg());

        return ServerResponse.createServerResponseBySuccess();
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
        payInfoVO.setPayedTime(DateUtils.date2String(payInfo.getPayedTime(),"yyyy-MM-dd HH:mm:ss"));
        payInfoVO.setPayPlatform(payInfo.getPayPlatform());
        payInfoVO.setOrderNo(payInfo.getOrderNo());
        payInfoVO.setCreateTime(DateUtils.date2String(payInfo.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));

        return payInfoVO;
    }

}
