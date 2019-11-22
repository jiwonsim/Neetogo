package com.jiwon.neetogo.dto.sodp;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@XmlRootElement(name = "RESULT")
public class Result {
    @SerializedName("CODE")
    private String code;
    @SerializedName("MESSAGE")
    private String message;
}
