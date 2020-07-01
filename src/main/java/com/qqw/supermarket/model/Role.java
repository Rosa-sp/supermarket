package com.qqw.supermarket.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class Role implements Serializable {

    private Integer id;

    private String name;

    private String description;

    private Boolean del;

    private Short status;

    private Date createAt;

    private Date updateAt;

    private List<Permission> permissions;

    private static final long serialVersionUID = 1L;

}