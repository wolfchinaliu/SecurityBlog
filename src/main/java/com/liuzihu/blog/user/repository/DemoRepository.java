package com.liuzihu.blog.user.repository;

import com.liuzihu.blog.user.domain.Demo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * @author Jerry
 * @version 1.0
 * @Description
 * @Date Create by 2018-07-10 14:11
 * @Modified by
 */
@Component
public interface DemoRepository extends JpaRepository<Demo,Long> {
}
