package com.jiwon.neetogo.service;

import com.jiwon.neetogo.dto.StationDTO;
import com.jiwon.neetogo.entity.FavoriteEntity;
import com.jiwon.neetogo.repository.FavoriteRepo;
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
public class FavoriteService {
    @Autowired
    FavoriteRepo favoriteRepo;
    @Autowired
    StationRepo stationRepo;

    public DefaultRes getFavoriteInfo() {
        try {
            List<FavoriteEntity> favoriteEntities = favoriteRepo.findAll();
            if (favoriteEntities == null) return new DefaultRes(StatusCode.OK, ResponseMessage.NO_DATA);
            return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_DATA, favoriteEntities);
        }
        catch (Exception e) {
            log.error(e.getMessage());
            return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessage.DB_ERROR);
        }
    }

    public DefaultRes saveFavorite(StationDTO stationDTO) {
        try {
            FavoriteEntity favoriteEntity = new FavoriteEntity();
            favoriteEntity.setCode(stationDTO.getStationCd());
            favoriteEntity.setName(stationDTO.getStationNm());

            log.info(favoriteEntity.getCode());
            log.info(favoriteEntity.getName());
            favoriteRepo.save(favoriteEntity);
            return DefaultRes.res(StatusCode.OK, ResponseMessage.SAVE_DATA);
        } catch (Exception e) {
            log.error(e.getMessage());
            return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessage.DB_ERROR);
        }
    }

    public DefaultRes deleteFavorite(Long num) {
        try {
            favoriteRepo.deleteById(num);
            return DefaultRes.res(StatusCode.OK, ResponseMessage.DELETE_DATA);
        }
        catch (Exception e) {
            log.error(e.getMessage());
            return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessage.DB_ERROR);
        }
    }
}
