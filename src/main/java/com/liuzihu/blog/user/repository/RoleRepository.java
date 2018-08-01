package com.liuzihu.blog.user.repository;

import com.liuzihu.blog.user.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * @author Jerry
 * @version 1.0
 * @Description
 * @Date Create by 2018-06-01 8:09
 * @Modified by
 */
@Component
public interface RoleRepository extends JpaRepository<Role,Long> {
}
