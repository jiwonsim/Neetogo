package com.jiwon.neetogo.dto;

import lombok.Data;

import java.util.*;

@Data
public class RouteDTO {
    private String spendingTime; // 소요시간
    private String stationCnt; // 노선 수
    private List<RouteOfStationDTO> routeOfStation;
}
