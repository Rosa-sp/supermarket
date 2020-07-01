package com.qqw.supermarket.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Classname Permission
 * @Description TODO()
 * @Date 2020/1/3 8:57
 * @Author by Alex
 */
@Data
public class Permission implements Serializable {

    private Integer id;

    private Integer parentId;

    private String name;

    private String actionMethod;

    private String actionUrl;

    private String digest;

    private Integer type;

    private Boolean del;

    private Integer status;

    private Integer sort;

    private Date createAt;

    private Date updateAt;

    private List<Permission> children;

    //是否已经处理过
    private boolean cooked;

    //树形选择title标题
    private String title;

    private static final long serialVersionUID = 1L;

}
