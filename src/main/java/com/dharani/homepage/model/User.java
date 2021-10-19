package com.dharani.homepage.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
@Data
public class User {

    @Id
    @GeneratedValue
    private int userId;
    @ApiModelProperty(notes = "Name of the User")
    private String name;
    @ApiModelProperty(notes = "Email of the User")
    private String email;
    @ApiModelProperty(notes = "User Password")
    private String password;
    @ApiModelProperty(notes = "User Phone Number")
    private double phoneNo;
    //  Date dateOfBirth;
    @ApiModelProperty(notes = "User Address")
    private String doorNo_StreetName;
    private String district;
    private String state;
    private int pincode;
    private String country;
}