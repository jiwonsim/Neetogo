package com.jiwon.neetogo.dto;

import lombok.Data;

@Data
public class RouteOfStationDTO {
    private int order;
    private String stationCd;
    private String stationNm;
    private String lineNum;
    private String frCode;
    private String direction;
}
