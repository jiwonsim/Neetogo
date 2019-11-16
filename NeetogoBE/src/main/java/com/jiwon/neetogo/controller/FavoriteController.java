package com.jiwon.neetogo.controller;


import com.jiwon.neetogo.dto.StationDTO;
import com.jiwon.neetogo.service.FavoriteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/favorite")
public class FavoriteController {

    @Autowired
    FavoriteService favoriteService;

    @GetMapping("")
    public ResponseEntity getFavorite() {
        return new ResponseEntity(favoriteService.getFavoriteInfo(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity saveFavorite(@RequestBody final StationDTO stationDTO) {
        log.info(stationDTO.getStationNm());
        return new ResponseEntity(favoriteService.saveFavorite(stationDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{num}")
    public ResponseEntity deleteFavorite(@PathVariable final long num) {
        return new ResponseEntity(favoriteService.deleteFavorite(num), HttpStatus.OK);
    }

}
