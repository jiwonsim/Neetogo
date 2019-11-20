package com.jiwon.neetogo.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
public class SearchLocationOfSTNByIDService {
    @SerializedName("list_total_count")
    private int listTotalCnt;
    @SerializedName("RESULT")
    private Result result;
    private List<Row> row;
}
