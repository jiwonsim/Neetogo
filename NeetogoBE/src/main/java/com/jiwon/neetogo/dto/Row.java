package com.jiwon.neetogo.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "row")
public class Row {
    @SerializedName("STATION_CD")
    private String station_cd;
    @SerializedName("STATION_NM")
    private String station_nm;
    @SerializedName("LINE_NUM")
    private String line_num;
    @SerializedName("FR_CODE")
    private String fr_code;
    @SerializedName("CYBER_ST_CODE")
    private String cyber_st_code;
    @SerializedName("XPOINT")
    private String xpoint;
    @SerializedName("YPOINT")
    private String ypoint;
    @SerializedName("XPOINT_WGS")
    private String xpoint_wgs;
    @SerializedName("YPOINT_WGS")
    private String ypoint_wgs;


//    private String station_cd;
//    private String STATION_NM;
//    private String LINE_NUM;
//    private String FR_CODE;
//    private String CYBER_ST_CODE;
//    private String XPOINT;
//    private String YPOINT;
//    private String XPOINT_WGS;
//    private String YPOINT_WGS;

}
