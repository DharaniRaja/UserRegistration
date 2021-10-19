package com.dharani.homepage.DTO;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AddressDTO {

    private String name;
    private double phoneNo;
    private String doorNo_StreetName;
    private String district;
    private String state;
    private int pincode;
    private String country;

    public AddressDTO(String name, double phoneNo, String doorNo_StreetName, String district, String state, int pincode, String country) {
        this.name = name;
        this.phoneNo = phoneNo;
        this.doorNo_StreetName = doorNo_StreetName;
        this.district = district;
        this.state = state;
        this.pincode = pincode;
        this.country = country;
    }
}