package com.jiwon.neetogo.service;

import com.jiwon.neetogo.dto.FavoriteDTO;
import com.jiwon.neetogo.entity.Favorite;
import com.jiwon.neetogo.repository.FavoriteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class FavoriteService {
    @Autowired
    FavoriteRepository favoriteRepository;

    public void saveFavorite(Favorite favorite) {
        log.info("SAVE SERVICE!");
        favoriteRepository.save(favorite);
    }

    public void deleteFavorite(Favorite favorite) {
        favoriteRepository.delete(favorite);
    }

    public List<Favorite> selectFavorite() {
        favoriteRepository.findAll().forEach(fav->log.info(fav.toString()));
        return (List) favoriteRepository.findAll();
    }
}
