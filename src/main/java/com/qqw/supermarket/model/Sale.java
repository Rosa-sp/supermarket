package com.qqw.supermarket.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class Sale implements Serializable {
    private Integer id;

    private String listNum;

    private String goodsId;

    private String goodsName;

    private BigDecimal price;

    private Integer count;

    private Double money;

    private Date payTime;

    private Boolean del;

    private Boolean status;

    private String remark;

    private static final long serialVersionUID = 1L;

}