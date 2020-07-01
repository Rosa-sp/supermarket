package com.qqw.supermarket.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Purchase implements Serializable {
    private Integer id;

    private String goodsId;

    private String goodsName;

    private Double price;

    private Integer count;

    private Double money;

    private String provider;

    private Date time;

    private Boolean del;

    private String remark;

    private static final long serialVersionUID = 1L;

}