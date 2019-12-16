package com.jiwon.neetogo.dto;

import lombok.Data;

@Data
public class RouteStaitonDTO {
    private int order;
    private String stationNm;
    private String stationCd;
    private String lineNum;
    private String frCode;
}
