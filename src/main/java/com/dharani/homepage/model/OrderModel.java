package com.dharani.homepage.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
public class OrderModel {

    @Id
    @GeneratedValue
    private int orderId;
    private String name;
    private String email;
    private int uId;
    private double phoneNo;
    private String paymentType;
    private boolean paymentSuccessfull;
    private String doorNo_StreetName;
    private String district;
    private String state;
    private int pincode;
    private String country;

    public OrderModel(){

    }
    public void setOrderModel(String name, double phoneNo, String doorNo_StreetName, String district, String state, int pincode, String country) {
        this.name = name;
        this.phoneNo = phoneNo;
        this.doorNo_StreetName = doorNo_StreetName;
        this.district = district;
        this.state = state;
        this.pincode = pincode;
        this.country = country;
    }
}