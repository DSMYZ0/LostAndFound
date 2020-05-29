package com.qin;

import com.qin.interceptor.PortalLoginCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@SpringBootConfiguration
public class SpringbootConfig implements WebMvcConfigurer {

    @Autowired
    PortalLoginCheckInterceptor portalLoginCheckInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        List<String> includeUrl=new ArrayList<>();
        includeUrl.add("/portal/user/**");
        includeUrl.add("/portal/order/**");
        includeUrl.add("/portal/post/**");

        List<String> exclueUrl= new ArrayList<>();
        exclueUrl.add("/portal/user/login");
        exclueUrl.add("/portal/user/register");
        exclueUrl.add("/portal/order/callback");
        exclueUrl.add("/portal/post/retrieve");
        exclueUrl.add("/portal/post/carousel");

        registry.addInterceptor(portalLoginCheckInterceptor)
                .addPathPatterns(includeUrl)
                .excludePathPatterns(exclueUrl);
    }
}
