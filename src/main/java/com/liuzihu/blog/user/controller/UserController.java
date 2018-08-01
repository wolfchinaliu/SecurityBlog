package com.liuzihu.blog.user.controller;

import com.liuzihu.blog.user.domain.User;
import com.liuzihu.blog.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jerry
 * @version 1.0
 * @Description
 * @Date Create by 2018-06-01 10:14
 * @Modified by
 */
@RestController
@RequestMapping(value = "/api",produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {


    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/save",method = RequestMethod.POST)
    public User save(@RequestBody User user){
        return userService.save(user);
    }
    @RequestMapping(value = "/token/getToken",method = RequestMethod.GET)
    public String generationToken(@RequestParam("username") String username,@RequestParam("password") String password){
        User user = new User();
        user.setUserName(username);
        user.setPassword(password);
        return userService.genernationToken(user);
    }
}
