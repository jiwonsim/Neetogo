package com.jiwon.neetogo.dto.kakao.search;

import lombok.Data;

@Data
public class Meta {
    private String same_name;
    private String pageable_count;
    private String total_cout;
    private String is_end;
}
