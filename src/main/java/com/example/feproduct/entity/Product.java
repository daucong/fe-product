package com.example.feproduct.entity;

import lombok.Data;

@Data
public class Product {
    private Integer id;

    private String name;

    private float price;

    private String image;

    private Category category;

    private String category_id;
}
