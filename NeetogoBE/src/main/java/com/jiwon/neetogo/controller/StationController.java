package com.jiwon.neetogo.controller;


import com.jiwon.neetogo.service.StationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/search")
public class StationController {

    @Autowired
    StationService stationService;

    @GetMapping("")
    public ResponseEntity getStationByName(@RequestParam(value = "name", required = false) String name) {
//        log.info("station : " + stationNm);
        return new ResponseEntity(stationService.getStationByName(name), HttpStatus.OK);
    }
}
