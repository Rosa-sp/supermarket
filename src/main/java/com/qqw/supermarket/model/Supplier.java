package com.qqw.supermarket.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Supplier implements Serializable {
    private Integer id;

    private String supplierId;

    private String supplierName;

    private String address;

    private String phone;

    private String person;

    private Boolean del;

    private String remark;

    private static final long serialVersionUID = 1L;


}