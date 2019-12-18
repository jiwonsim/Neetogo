package com.jiwon.neetogo.service;

import com.jiwon.neetogo.dto.RouteDTO;
import com.jiwon.neetogo.dto.RouteOfStationDTO;
import com.jiwon.neetogo.dto.StartTimeDTO;
import com.jiwon.neetogo.dto.StationDTO;
import com.jiwon.neetogo.dto.kakao.geo.Documents;
import com.jiwon.neetogo.dto.kakao.geo.GEOResult;
import com.jiwon.neetogo.dto.sodp.LastTrainTime;
import com.jiwon.neetogo.entity.StationEntity;
import com.jiwon.neetogo.init.InitializeComponent;
import com.jiwon.neetogo.repository.StationRepo;
import com.jiwon.neetogo.search.model.ResultOfRoute;
import com.jiwon.neetogo.search.model.Station;
import com.jiwon.neetogo.search.service.SubwaySearcher;
import com.jiwon.neetogo.service.outer.KakaoDevelopers;
import com.jiwon.neetogo.service.outer.SeoulOpenData;
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
import java.util.Calendar;
import java.util.List;

@Slf4j
@Service
public class StationService {

    @Autowired
    StationRepo stationRepo;

    @Autowired
    SeoulOpenData seoulOpenData;

    @Autowired
    KakaoDevelopers kakaoDevelopers;

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

    public DefaultRes getLastTimes(String stationNm, String direction) {
        Calendar cal = Calendar.getInstance();
        int dayOfToday = cal.get(Calendar.DAY_OF_WEEK);

        String day;
        if (dayOfToday == 1) day = "3"; // 일요일
        else if (dayOfToday == 7) day = "2"; // 토요일
        else day = "1";

        List<StationEntity> station = stationRepo.findByStationNm(stationNm);
        if (station == null) return DefaultRes.res(StatusCode.OK, ResponseMessage.FAIL_TO_READ_DATA);

        String stationCd = station.get(0).getStationCd();

        LastTrainTime result = seoulOpenData.searchLastTrainTimeByID(stationCd, day, direction);
        if (!result.getInfo().getResult().getCode().equals("INFO-000")) {
            return DefaultRes.res(StatusCode.SERVICE_UNAVAILABLE, ResponseMessage.FAIL_TO_READ_DATA);
        }

        return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_DATA, result.getInfo().getRow());
    }

    private double transString2Double(String number) {
        return Double.parseDouble(number);
    }

    public double calcDistance(Documents startCoord, Documents endCoord) {
        double startX = transString2Double(startCoord.getX());
        double startY = transString2Double(startCoord.getY());

        double endX = transString2Double(endCoord.getX());
        double endY = transString2Double(endCoord.getY());

        double result = Math.sqrt(Math.pow(startX - endX, 2) - Math.pow(startY - endY, 2));
        System.out.println(result);
        System.out.println((int)result);
        return result;
    }

    public int calcWalkingMinute(double distance) {
        // 1km = 1000m = 10분 -> 1m = 10/1000분 = 1/100분 = 대충 1초
        int second = (int) distance;
        if (second <= 60) return 1;
        int minute = second / 60;

        return minute + 1;
    }

    public DefaultRes getDistanceByWGS(String startLongitude, String startLatitude, String endLongitude, String endLatitude, String time) {
        Documents startTransRes = kakaoDevelopers.transformCoordinate(startLongitude, startLatitude);
        Documents endTransRes = kakaoDevelopers.transformCoordinate(endLongitude, endLatitude);
        if (startTransRes == null || endTransRes == null)
            return DefaultRes.res(StatusCode.OK, ResponseMessage.NO_DATA); // 에러

        double distanceWTM = calcDistance(startTransRes, endTransRes); // 1WTM = 1m
        int minute = calcWalkingMinute(distanceWTM);

        StartTimeDTO startTimeDTO = new StartTimeDTO();
        String[] arrivalTime = time.split(":");
        int startHour = Integer.parseInt(arrivalTime[0]), startMin = Integer.parseInt(arrivalTime[1]);
        if (minute > 60) {
            startHour -= (minute / 60);
        }
        startMin -= (minute % 60);

        startTimeDTO.setSpendingMinute(minute);
        startTimeDTO.setStartingTime(startHour + ":" + startMin);

        return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_DATA, startTimeDTO);
    }
}
