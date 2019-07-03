package com.jiwon.neetogo.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Data
@Builder
@Entity
public class Favorite {
    @Id
    @GeneratedValue
    private int num;

    private String name;
    private String code;
     private double latitude;
    private double longitude;

    @Builder
    public Favorite(int num, String name, String code, double latitude, double longitude) {
        this.num = num;
        this.name = name;
        this.code = code;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
