package com.qqw.supermarket.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Data
public class Goods implements Serializable {
    private Integer id;

    private String number;

    private String name;

    private String forShort;

    private Double purchasePrice;

    private Double sellingPrice;

    private String placeOrigin;

    private String specification;

    private Integer unitId;

    private Integer categoryId;

    private String categoryNumber;

    private String categoryName;

    private Integer status;

    private Integer providerId;

    private String providerUmber;

    private String providerName;

    private String brandName;

    private String memo;

    private List<Categorys> children;

    private static final long serialVersionUID = 1L;


}