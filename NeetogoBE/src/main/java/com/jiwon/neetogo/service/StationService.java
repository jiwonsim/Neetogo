package com.jiwon.neetogo.service;

import com.jiwon.neetogo.dto.RouteDTO;
import com.jiwon.neetogo.dto.RouteOfStationDTO;
import com.jiwon.neetogo.dto.StationDTO;
import com.jiwon.neetogo.entity.StationEntity;
import com.jiwon.neetogo.init.InitializeComponent;
import com.jiwon.neetogo.repository.StationRepo;
import com.jiwon.neetogo.search.model.ResultOfRoute;
import com.jiwon.neetogo.search.model.Station;
import com.jiwon.neetogo.search.service.SubwaySearcher;
import com.jiwon.neetogo.util.DefaultRes;
import com.jiwon.neetogo.util.ResponseMessage;
import com.jiwon.neetogo.util.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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

    public String getStationNm(String stationNm) {
        List<StationEntity> result = stationRepo.findByStationNm(stationNm);
        if (result == null) return null;
        return result.get(0).getStationNm();
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
        // This service method only return >>String List<<
        InitializeComponent initializeComponent = new InitializeComponent(true);

        // Find full name of station
        String fullStartNm = getStationNm(start);
        String fullEndNm = getStationNm(end);

        if (fullEndNm == null || fullStartNm == null) {
            return DefaultRes.res(StatusCode.OK, ResponseMessage.FAIL_TO_READ_DATA); // Wrong input
        }

        ResultOfRoute resultOfRoute = initializeComponent.searchRoute(fullStartNm, fullEndNm);
        ArrayList<String> nmList = resultOfRoute.getStationNms();

        List<RouteOfStationDTO> searchingStationResult = new ArrayList<>();
        for (int i = 0; i < nmList.size(); i++) {
            String name = nmList.get(i);

            List<StationEntity> stations = stationRepo.findByStationNm(name);
            StationEntity station = stations.get(0);

            RouteOfStationDTO ele = new RouteOfStationDTO();
            ele.setOrder(i + 1);
            ele.setStationNm(station.getStationNm());
            ele.setLineNum(station.getLineNum());
            ele.setStationCd(station.getStationCd());
            ele.setFrCode(station.getFrCode());

            searchingStationResult.add(ele);
        }

        // 상행선 하행선을 찾기 위해 linkNm.txt 탐색
        String path = "/Users/simjiwon/Desktop/Project/Neetogo/NeetogoBE/src/main/java/com/jiwon/neetogo/search/resource/files";
        File file = new File(path + "/linkNm.txt");

        byte[] fileBytes = new byte[(int) file.length()];
        try (FileInputStream in = new FileInputStream(file)) {
            in.read(fileBytes, 0, fileBytes.length);
        }

        String[] datas = new String(fileBytes).split("\n");
        if (datas.length <= 0) new IOException("WRONG_DOCUMENTS_STYLE");

        for (int i = 1; i < searchingStationResult.size(); i++) {
            RouteOfStationDTO curStn = searchingStationResult.get(i - 1);
            RouteOfStationDTO nextStn = searchingStationResult.get(i);
            for (String data : datas) {
                String[] words = data.replace("\r", "").split(",");
                if (words[0].equals(curStn.getStationNm())) {
                    if (words[1].equals(nextStn.getStationNm())) { // 상행
                        searchingStationResult.get(i - 1).setDirection("1");
                    }
                    if (words[2].equals(nextStn.getStationNm())) { // 하행
                        searchingStationResult.get(i - 1).setDirection("2");
                    }
                }
            }
        }

        if (searchingStationResult == null) return DefaultRes.res(StatusCode.OK, ResponseMessage.NO_DATA);

        RouteDTO result = new RouteDTO();
        result.setSpendingTime(String.valueOf(resultOfRoute.getSpendingTime()));
        result.setRouteOfStation(searchingStationResult);
        result.setStationCnt(String.valueOf(searchingStationResult.size()));

        return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_DATA, result);
    }
}
