package com.jiwon.neetogo.dto.kakao.geo;

import lombok.Data;

import java.util.List;

@Data
public class GEOResult {
    private Meta meta;
    private List<Documents> documents;
}
