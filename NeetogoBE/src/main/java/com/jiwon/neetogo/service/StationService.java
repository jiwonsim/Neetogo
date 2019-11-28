package com.jiwon.neetogo.service;

import com.jiwon.neetogo.entity.StationEntity;
import com.jiwon.neetogo.init.InitializeComponent;
import com.jiwon.neetogo.repository.StationRepo;
import com.jiwon.neetogo.search.model.Station;
import com.jiwon.neetogo.search.service.SubwaySearcher;
import com.jiwon.neetogo.util.DefaultRes;
import com.jiwon.neetogo.util.ResponseMessage;
import com.jiwon.neetogo.util.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class StationService {

    @Autowired
    StationRepo stationRepo;

    public DefaultRes saveStationInfo(StationEntity stationEntity) {
        try {
            stationRepo.save(stationEntity);
            return DefaultRes.res(StatusCode.OK, ResponseMessage.SAVE_DATA);
        } catch (Exception e) {
            log.error(e.getMessage());
            return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessage.DB_ERROR);
        }
    }

    public DefaultRes getStationByName(String stationNm) {
        try {
            List<StationEntity> stationEntity = stationRepo.findByStationNm(stationNm);
            if (stationEntity == null) {
                return DefaultRes.res(StatusCode.OK, ResponseMessage.NO_DATA);
            }
            return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_DATA, stationEntity);
        }
        catch (Exception e) {
            log.error(e.getMessage());
            return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessage.DB_ERROR);
        }
    }

    public List<StationEntity> getStationInfoByName(String stationNm) {
        try {
            List<StationEntity> stationEntity = stationRepo.findByStationNm(stationNm);
            return stationEntity;
        }
        catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public DefaultRes getStationRoute(String start, String end) throws Exception{



        InitializeComponent initializeComponent = new InitializeComponent(true);
        ArrayList<Station> result = initializeComponent.searchRoute(start, end);
        if (result == null) return DefaultRes.res(StatusCode.OK, ResponseMessage.NO_DATA);
        else return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_DATA, result);
    }
}
