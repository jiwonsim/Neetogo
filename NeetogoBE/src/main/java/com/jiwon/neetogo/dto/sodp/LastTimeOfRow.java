package com.jiwon.neetogo.dto.sodp;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "row")
public class LastTimeOfRow {
    @SerializedName("FR_CODE")
    private String frCode;
    @SerializedName("STATION_CD")
    private String stationCd;
    @SerializedName("STATION_NM")
    private String stationNm;
    @SerializedName("SUBWAYENAME")
    private String subwayEname;

    @Temporal(TemporalType.DATE)
    @SerializedName("LEFTTIME")
    private String leftTime;
    @SerializedName("WEEK_TAG")
    private String weekTag;
    @SerializedName("INOUT_TAG")
    private String inoutTag;
}
