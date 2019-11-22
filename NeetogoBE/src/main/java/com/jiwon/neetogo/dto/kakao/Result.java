package com.jiwon.neetogo.dto.kakao;

import lombok.Data;

import java.util.List;

@Data
public class Result {
    private Meta meta;
    private List<Documents> documents;
}
