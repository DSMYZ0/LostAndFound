package com.qin.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.qin.common.ServerResponse;
import com.qin.service.IPayService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/portal/order/")
public class AlipayController {

    @Resource
    IPayService payService;

    @Value("${alipay.publicKey}")
    private String publicKey;

    /**
     * 支付接口
     * */
    @RequestMapping("pay")
    public ServerResponse pay(Long orderNo){

        return payService.pay(orderNo);
    }

    /**
     * 支付宝回调
     * */
    @RequestMapping("callback")
    public String alipayCallback(HttpServletRequest request){

        //验证支付宝签名
        Map<String,String[]> params=request.getParameterMap();
        if(params==null||params.size()==0)
            return "fail";

        Map<String,String> signParam=new HashMap<>();
        Set<String> keys=params.keySet();
        Iterator<String> iterator=keys.iterator();

        while (iterator.hasNext()){
            String key=iterator.next();
            String[] values=params.get(key);
            StringBuilder stringBuilder=new StringBuilder();

            for(String value:values){
                stringBuilder.append(value+",");
            }

            String value=stringBuilder.toString();
            value=value.substring(0,value.length()-1);
            signParam.put(key,value);
        }

        Boolean checkSign=false;
        signParam.remove("sign_type");
        try {
            checkSign = AlipaySignature.rsaCheckV2(signParam,publicKey,"utf-8","RSA2");
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        if(checkSign){
            return payService.callback(signParam);
        }

        //返回success支付宝服务器将不再回调接口
        return "fail";
    }
}
