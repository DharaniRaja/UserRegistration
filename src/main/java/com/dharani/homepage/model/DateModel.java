package com.dharani.homepage.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Timestamp;

@Data
public class DateModel {

    private int dateId;
    private Date date;
    private Timestamp time;
    /*  @ManyToOne
      private User user;*/
    private int userId;

}