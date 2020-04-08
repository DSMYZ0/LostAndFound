package com.qin.service.impl;

import com.qin.common.OrderStatusEnum;
import com.qin.common.ResponseCode;
import com.qin.common.ServerResponse;
import com.qin.common.TradeStatusEnum;
import com.qin.dao.CarouselMapper;
import com.qin.dao.PayInfoMapper;
import com.qin.exception.LafException;
import com.qin.pojo.Carousel;
import com.qin.pojo.PayInfo;
import com.qin.service.IOrderService;
import com.qin.service.IPayService;
import com.qin.util.DateUtils;
import com.qin.util.OrderInfoUtil2_0;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

@Service
public class PayService implements IPayService {

    @Value("${alipay.appid}")
    private String APPID;
    @Value("${alipay.rsa2_private}")
    private String RSA2_PRIVATE;
    @Value("${alipay.notify_url}")
    private String NOTIFY_URL;

    @Resource
    PayInfoMapper payInfoMapper;
    @Resource
    IOrderService orderService;
    @Resource
    CarouselMapper carouselMapper;

    @Override
    public ServerResponse pay(Long orderNo) {

        if (StringUtils.isBlank(APPID) || (StringUtils.isBlank(RSA2_PRIVATE) )) {
            return ServerResponse.createServerResponseByFail(ResponseCode.PAY_PARAM_ERROR.getCode(),ResponseCode.PAY_PARAM_ERROR.getMsg());
        }

        //根据订单号查询订单
        PayInfo payInfo=payInfoMapper.findPayInfoByOrderNo(orderNo);
        if(payInfo==null)
            ServerResponse.createServerResponseByFail(ResponseCode.ORDER_NOT_EXIST.getCode(),ResponseCode.ORDER_NOT_EXIST.getMsg());

        // 生成支付信息
        Map<String,String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID,true,payInfo,NOTIFY_URL);
        String orderParam=OrderInfoUtil2_0.buildOrderParam(params);

        String sign = OrderInfoUtil2_0.getSign(params, RSA2_PRIVATE, true);
        final String orderInfo = orderParam + "&" + sign;

        return ServerResponse.createServerResponseBySuccess(orderInfo);
    }

    @Override
    public String callback(Map<String, String> map) {

        //获取订单编号并校验
        Long orderNo=Long.valueOf(map.get("out_trade_no"));
        PayInfo payInfo=payInfoMapper.findPayInfoByOrderNo(orderNo);
        if(payInfo==null)
            return "success";

        //校验订单状态
        if(payInfo.getPlatformStatus() >= OrderStatusEnum.ORDER_PAYED.getCode()){
            return "success";
        }

        String tradeStatus=map.get("trade_status");
        Date payedTime= DateUtils.string2Date(map.get("gmt_payment"),"yyyy-MM-dd HH:mm:ss");
        //修改订单
        ServerResponse serverResponse=orderService.updateOrder(orderNo, TradeStatusEnum.codeof(tradeStatus),payedTime);
        if(!serverResponse.isSuccess())
            return "fail";
        //更新轮播表
        Carousel carousel=new Carousel();
        carousel.setPostId(payInfo.getPostId());
//        carousel.setOrder(1);
        carousel.setTime(payedTime);
        int countCarousel=carouselMapper.insert(carousel);
        if(countCarousel==0)
            return "fail";

        return "success";
    }
}
