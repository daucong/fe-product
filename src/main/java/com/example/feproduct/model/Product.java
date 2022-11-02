package com.example.feproduct.model;

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
