package com.liuzihu.blog.security;

import com.liuzihu.blog.utils.MyPassWordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Jerry
 * @version 1.0
 * @Description Security 配置类，设置那些请求拦截和那些请求需要权限控制
 * @Date Create by 2018-05-31 15:21
 * @Modified by
 */
@SuppressWarnings("all")
@Configuration  /** 声明配置类 **/
@EnableWebSecurity /** 启用security安全配置 **/
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new MyPassWordEncoder();
    }

    @Autowired
    private AuthorityFilter authorityFilter;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
            /**
             * 设置禁用session和csrf 配置API权限认证
             */
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests() //验证请求
                .antMatchers("/api/admin/*").hasRole("ADMIN")
                .antMatchers("/token/*").permitAll()
                .antMatchers("/test/*").permitAll()
                .antMatchers("/api/user/*").hasRole("USER")
                .and()
                .addFilterBefore(authorityFilter,UsernamePasswordAuthenticationFilter.class);
    }
}
