package com.liuzihu.blog.test;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jerry
 * @version 1.0
 * @Description
 * @Date Create by 2018-06-01 17:12
 * @Modified by
 */
@RequestMapping(value = "/test",produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class TestController {

    @RequestMapping(value = "hello",method = RequestMethod.GET)
    public Map test(){
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("message", "hello world");
        return map;
    }
}
