package com.jiwon.neetogo.controller;

import com.jiwon.neetogo.dto.FavoriteDTO;
import com.jiwon.neetogo.entity.Favorite;
import com.jiwon.neetogo.service.FavoriteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/save")
public class FavoriteController {
    @Autowired
    FavoriteService favoriteService;

    @PostMapping("")
    public ResponseEntity saveFavorite(@RequestBody final FavoriteDTO favoriteDTO) {

        Favorite favorite = Favorite.builder().num(favoriteDTO.getNum())
                .name(favoriteDTO.getName())
                .code(favoriteDTO.getCode())
                .latitude(favoriteDTO.getLatitude())
                .longitude(favoriteDTO.getLongitude())
                .build();
        log.info(String.valueOf(favorite.getNum()));
        log.info(String.valueOf(favorite.getName()));
        log.info(String.valueOf(favorite.getCode()));
        log.info(String.valueOf(favorite.getLatitude()));
        log.info(String.valueOf(favorite.getLongitude()));
        favoriteService.saveFavorite(favorite);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity getFavorite() {
        return new ResponseEntity<>(favoriteService.selectFavorite(), HttpStatus.OK);
    }

}
