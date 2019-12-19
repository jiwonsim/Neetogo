package com.jiwon.neetogo.dto.sodp;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class LastTrainTime {
    @SerializedName("SearchLastTrainTimeByIDService")
    private SearchLastTrainTimeByIDService info;
}
