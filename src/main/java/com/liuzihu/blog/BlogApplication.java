package com.liuzihu.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlogApplication {

    public static void main(String[] args) {
        String s = null;
        System.out.println(s.toString());
        SpringApplication.run(BlogApplication.class, args);
    }
}
