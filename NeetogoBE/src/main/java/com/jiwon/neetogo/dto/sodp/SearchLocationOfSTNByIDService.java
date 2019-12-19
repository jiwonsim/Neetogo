package com.jiwon.neetogo.dto.sodp;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class SearchLocationOfSTNByIDService {
    private int list_total_count;
    @SerializedName("RESULT")
    private Result result;
    private List<LocOfRow> row;
}
