package com.jiwon.neetogo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class StationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long num;
    private String stationCd;
    private String stationNm;
    private String lineNum;
    private String frCode;
}
