package com.jiwon.neetogo.dto;


import com.jiwon.neetogo.entity.Favorite;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Data
public class FavoriteDTO {
    private int num;
    private String name;
    private String code;
    private double latitude;
    private double longitude;

//    @Builder
//    public FavoriteDTO(int num, String name, String code, double latitude, double longitude) {
//        this.num = num;
//        this.name = name;
//        this.code = code;
//        this.latitude = latitude;
//        this.longitude = longitude;
//    }

}
