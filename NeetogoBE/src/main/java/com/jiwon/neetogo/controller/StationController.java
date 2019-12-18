package com.jiwon.neetogo.controller;


import com.jiwon.neetogo.dto.LocationDTO;
import com.jiwon.neetogo.service.StationService;
import com.jiwon.neetogo.service.outer.KakaoDevelopers;
import com.jiwon.neetogo.service.outer.SeoulOpenData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/search")
public class StationController {

    @Autowired
    StationService stationService;

    @Autowired
    SeoulOpenData seoulOpenData;

    @Autowired
    KakaoDevelopers kakaoDevelopers;

    @GetMapping("")
    public ResponseEntity getStationByName(@RequestParam(value = "name", required = true) String name) {
        return new ResponseEntity(stationService.getStationByName(name), HttpStatus.OK);
    }

    @GetMapping("/{code}")
    public ResponseEntity getStationByCode(@PathVariable String code) throws Exception {
        log.info("code : " + code);
        return new ResponseEntity(seoulOpenData.searchLocationOfSTNByID(code), HttpStatus.OK);
    }

    @GetMapping("/here")
    public ResponseEntity getStationByHereLoc(@RequestParam(value = "x") String longitude,
                                    @RequestParam(value = "y") String latitude) {
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setLatitude(latitude);
        locationDTO.setLongitude(longitude);
        return new ResponseEntity(kakaoDevelopers.searchStationByHereLoc(locationDTO), HttpStatus.OK);
    }

    @GetMapping("/route")
    public ResponseEntity getStationRoute(@RequestParam(value = "start") String start,
                                             @RequestParam(value = "end") String end) throws Exception {
        return new ResponseEntity(stationService.getStationRoute(start, end), HttpStatus.OK);
    }

    @GetMapping("/lastTimes")
    public ResponseEntity getLastStationTime(@RequestParam(value = "stn") String stationNm,
                                   @RequestParam(value = "dir") String direction) {
        // 37.466613 126.889249
        return new ResponseEntity(stationService.getLastTimes(stationNm, direction), HttpStatus.OK);
    }

    @GetMapping("/startingTime")
    public ResponseEntity getStartingTime(@RequestParam(value = "startX") String startLongitude,
                                          @RequestParam(value = "startY") String startLatitude,
                                          @RequestParam(value = "endX") String endLongitude,
                                          @RequestParam(value = "endY") String endLatitude,
                                          @RequestParam(value = "time") String time) {
        return new ResponseEntity(stationService.getDistanceByWGS(startLongitude, startLatitude, endLongitude, endLatitude, time), HttpStatus.OK);

    }

}
