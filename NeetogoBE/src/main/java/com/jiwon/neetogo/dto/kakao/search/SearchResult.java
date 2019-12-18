package com.jiwon.neetogo.dto.kakao.search;

import lombok.Data;

import java.util.List;

@Data
public class SearchResult {
    private Meta meta;
    private List<Documents> documents;
}
