package com.qin.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qin.common.Const;
import com.qin.common.ResponseCode;
import com.qin.common.ServerResponse;
import com.qin.pojo.vo.UserVO;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

@Component
public class PortalLoginCheckInterceptor implements HandlerInterceptor {

    //请求到达Controller之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session=request.getSession();
        UserVO userVO=(UserVO) session.getAttribute(Const.CURRENT_USER);

        if(userVO==null){
            //用户未登录 重写Response
            ServerResponse serverResponse=ServerResponse.createServerResponseByFail(ResponseCode.NOT_LOGIN.getCode(),ResponseCode.NOT_LOGIN.getMsg());
            response.reset();
            response.addHeader("Content-Type","application/json;charset=utf-8");

            PrintWriter printWriter=response.getWriter();
            ObjectMapper objectMapper=new ObjectMapper();
            printWriter.write(objectMapper.writeValueAsString(serverResponse));
            printWriter.flush();
            printWriter.close();

            return false;
        }

        return true;
    }

    //请求处理完成后
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        System.out.println("========postHandle========");

    }

    ///客户端接收响应后
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        System.out.println("========afterCompletion========");

    }
}
