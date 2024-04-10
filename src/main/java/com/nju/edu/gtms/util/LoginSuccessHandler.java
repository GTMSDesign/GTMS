package com.nju.edu.gtms.util;

import cn.hutool.json.JSONUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {


    @Autowired
    JwtConfig jwtConfig;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        response.setContentType("application/json");
//        response.setCharacterEncoding("utf-8");
//        response.getWriter().write("loginOK");

        Map<String,String> map = new HashMap<>();
        String token = jwtConfig.createJWT(authentication.getPrincipal().toString(),authentication.getAuthorities().toArray()[0].toString());
        map.put("token",token);
        Result result = Result.success(map);
        WebUtils.renderString(response, JSONUtil.toJsonStr(result));
        System.out.println(authentication.getCredentials());
        System.out.println(authentication.getPrincipal());
        System.out.println(authentication.getAuthorities());
    }
}
