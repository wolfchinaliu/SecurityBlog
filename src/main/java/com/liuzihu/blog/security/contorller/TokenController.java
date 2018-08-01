package com.liuzihu.blog.security.contorller;

import com.liuzihu.blog.user.domain.User;
import com.liuzihu.blog.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jerry
 * @version 1.0
 * @Description
 * @Date Create by 2018-06-01 15:56
 * @Modified by
 */
@RestController
@RequestMapping(value = "/token",produces = MediaType.APPLICATION_JSON_VALUE)
public class TokenController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getToken",method = RequestMethod.GET)
    public Map<String, Object> getToken(@RequestParam(value = "password") String password, @RequestParam(value = "username") String username) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            System.out.println(authentication.toString());
        }
        User user = new User();
        user.setUserName(username);
        user.setPassword(password);
        user.setName(username);
        user.setCreateDate(new Date());
        user.setCreateDate(new Date());
        Map<String, Object> token = new HashMap();
        token.put("token", userService.genernationToken(user));
        return token;
    }
}
