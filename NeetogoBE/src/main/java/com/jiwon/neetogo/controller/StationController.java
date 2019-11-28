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
    public ResponseEntity getStationByHereLoc(@RequestParam(value = "x") String x,
                                    @RequestParam(value = "y") String y) {
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setLatitude(x);
        locationDTO.setLongitude(y);
        return new ResponseEntity(kakaoDevelopers.searchStationByHereLoc(locationDTO), HttpStatus.OK);
    }

    @GetMapping("/route")
    public ResponseEntity getLastStationTime(@RequestParam(value = "start") String start,
                                             @RequestParam(value = "end") String end) throws Exception {
        return new ResponseEntity(stationService.getStationRoute(start, end), HttpStatus.OK);

    }

}
