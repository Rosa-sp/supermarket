package com.qqw.supermarket.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Categorys implements Serializable {
    private Integer id;

    private String number;

    private String name;

    private Integer fresh;

    private Integer parentId;

    private Boolean status;

    private Boolean leaf;

    private Integer nuitId;

    private String memo;

    private String spate1;

    private List<Categorys> children;

    //是否已经处理过
    private boolean cooked;

    //树形选择title标题
    private String title;

    private static final long serialVersionUID = 1L;

}