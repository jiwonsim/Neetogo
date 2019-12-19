package com.jiwon.neetogo.repository;


import com.jiwon.neetogo.entity.FavoriteEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface FavoriteRepo extends CrudRepository<FavoriteEntity, Long> {
    List<FavoriteEntity> findAll();
}
