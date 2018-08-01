package com.liuzihu.blog.user.service;

import com.liuzihu.blog.user.domain.Demo;
import com.liuzihu.blog.user.repository.DemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jerry
 * @version 1.0
 * @Description
 * @Date Create by 2018-07-10 14:12
 * @Modified by
 */
@Service
public class DemoService {

    @Autowired
    private DemoRepository demoRepository;

    public Demo save(Demo demo) {
        return demoRepository.save(demo);
    }

}
