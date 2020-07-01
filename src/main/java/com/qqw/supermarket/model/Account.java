package com.qqw.supermarket.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Account implements Serializable {

    private String id;

    private String account;

    private String password;

    private String realName;

    private String salt;

    private String headPic;

    private Boolean locked;

    private Date lastLoginAt;

    private Boolean del;

    private Integer status;

    private Date createAt;

    private Date updateAt;

    private static final long serialVersionUID = 1L;

}