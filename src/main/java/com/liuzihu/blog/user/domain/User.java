package com.liuzihu.blog.user.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author Jerry
 * @version 1.0
 * @Description
 * @Date Create by 2018-05-31 16:45
 * @Modified by
 */

@Data
@Entity
@Table(name = "user")
public class User implements UserDetails {

    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name = "update_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    @Column(name = "description")
    private String description;

    @Column(name = "is_enable")
    private boolean isEnable;




    public User() {

    }

    public boolean getIsEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }

    @ManyToMany(cascade = CascadeType.REFRESH, targetEntity = Role.class, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", inverseJoinColumns = @JoinColumn(name = "user_id"), joinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
