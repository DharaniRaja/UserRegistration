package com.dharani.homepage.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class ProductModel {

    @Id
    @GeneratedValue
    private int productId;
    private String name;
    private String imagePath;
    private String color;
    private String category;
    private String description;
    private int quantity;
    private float price;
}