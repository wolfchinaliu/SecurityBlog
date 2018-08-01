package com.liuzihu.blog.security;

import com.liuzihu.blog.user.domain.User;
import com.liuzihu.blog.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author Jerry
 * @version 1.0
 * @Description
 * @Date Create by 2018-06-01 8:28
 * @Modified by
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //检验是否存在该用户，存在即获取该用户

        if (username == null) {
            return null;
        }
        User user = userRepository.findByUserName(username);
        if (user == null) {
            return null;
        }
        return user;
    }
}
