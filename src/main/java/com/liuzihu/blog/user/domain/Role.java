package com.liuzihu.blog.user.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * @author Jerry
 * @version 1.0
 * @Description
 * @Date Create by 2018-05-31 16:57
 * @Modified by
 */
@Data
@Entity
@Table(name = "role")
public class Role implements GrantedAuthority {
    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "permissions")
    private String permissions;

    @Column(name = "description")
    private String description;


    public Role() {

    }

    @Override
    public String getAuthority() {
        return permissions;
    }
}
