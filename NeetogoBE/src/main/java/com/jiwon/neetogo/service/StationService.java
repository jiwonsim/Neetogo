package com.jiwon.neetogo.service;

import com.jiwon.neetogo.entity.Station;
import com.jiwon.neetogo.repository.StationRepo;
import com.jiwon.neetogo.util.DefaultRes;
import com.jiwon.neetogo.util.ResponseMessage;
import com.jiwon.neetogo.util.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class StationService {

    @Autowired
    StationRepo stationRepo;

    public DefaultRes saveStationInfo(Station station) {
        try {
            stationRepo.save(station);
            return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_DATA);
        } catch (Exception e) {
            log.error(e.getMessage());
            return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessage.DB_ERROR);
        }
    }
}
