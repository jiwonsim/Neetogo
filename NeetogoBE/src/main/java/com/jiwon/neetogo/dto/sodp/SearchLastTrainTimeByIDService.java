package com.jiwon.neetogo.dto.sodp;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class SearchLastTrainTimeByIDService {
    @SerializedName("list_total_count")
    private int listTotalCnt;
    @SerializedName("RESULT")
    private Result result;
    private List<LastTimeOfRow> row;
}
