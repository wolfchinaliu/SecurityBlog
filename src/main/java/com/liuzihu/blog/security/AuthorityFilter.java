package com.liuzihu.blog.security;

import com.liuzihu.blog.user.domain.User;
import com.liuzihu.blog.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Jerry
 * @version 1.0
 * @Description
 * @Date Create by 2018-06-01 9:18
 * @Modified by
 */
@Component
public class AuthorityFilter extends GenericFilterBean {
    private final String HEADER_NAME = "Authorization";

    @Autowired
    private TokenUtils tokenUtils;


    /**
     * 从请求头解析出token
     * @param request
     * @return token
     */
    private String resolveToken(HttpServletRequest request){
        String token = request.getHeader(HEADER_NAME);
        if(token==null||!token.startsWith("Bearer ")){
            return null;
        } else{
            return token.substring(7);
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest servletRequest = (HttpServletRequest) request;
        //检验方法
        //通过JWT 获取token
        String token = resolveToken(servletRequest);
        //判断token是否为空
        if (token == null) {
            return;
        }
        //检验token
        if (tokenUtils.verifyToken(token)) {
            Authentication authentication = tokenUtils.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
        cleanAuthentication();
    }

    private void cleanAuthentication() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }
}
