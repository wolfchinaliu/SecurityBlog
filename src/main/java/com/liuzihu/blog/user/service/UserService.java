package com.liuzihu.blog.user.service;

import com.liuzihu.blog.security.MyUserDetailsService;
import com.liuzihu.blog.user.domain.User;
import com.liuzihu.blog.user.repository.UserRepository;
import com.liuzihu.blog.utils.MyPassWordEncoder;
import com.liuzihu.blog.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Jerry
 * @version 1.0
 * @Description
 * @Date Create by 2018-06-01 10:31
 * @Modified by
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenUtils tokenUtils;

    public User save(User user) {
//        User byUserName = userRepository.findByUserName(user.getUsername());
        return userRepository.save(user);
    }
    public String genernationToken(User user){
        User byUserName = userRepository.findByUserName(user.getUsername());
        if (byUserName.getPassword().equals(new MyPassWordEncoder().encode(user.getPassword()))) {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(byUserName.getUsername(),byUserName.getPassword());
            return tokenUtils.genernationToken(authenticationToken, user);
        }
        return "user is not valid";
    }
}
