package com.dharani.homepage.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Cart {
    @Id
    @GeneratedValue
    private int cartId;
    private int productId;
    private int userId;
}
