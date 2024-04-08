package com.nju.edu.gtms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity //开启SpringSecurity,注册大量的过滤器
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        .requestMatchers("/account/**").permitAll()  // 对/account/**的请求不需要认证
                        .anyRequest().authenticated()  // 其他所有请求需要认证
                );
        http.formLogin(formLogin->
                formLogin
                        .loginProcessingUrl("/account/login")
                        //todo：successHandler和failureHandler
        );
        http.csrf(Customizer.withDefaults());//关闭跨域漏洞防御
        http.cors(Customizer.withDefaults());//跨域拦截关闭
        http.logout(logout->logout.invalidateHttpSession(true));

        return http.build();
    }



    //todo:用户加密存储实现
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
