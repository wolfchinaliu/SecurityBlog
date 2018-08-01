package com.liuzihu.blog.user.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Jerry
 * @version 1.0
 * @Description
 * @Date Create by 2018-07-10 14:09
 * @Modified by
 */
@Entity
@Table(name = "demo")
@Data
public class Demo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;
}
