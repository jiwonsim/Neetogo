package com.jiwon.neetogo.dto;

import lombok.Data;

@Data
public class StationDTO {
    private Long num;
    private String stationCd;
    private String stationNm;
    private String lineNum;
    private String frCode;
}
