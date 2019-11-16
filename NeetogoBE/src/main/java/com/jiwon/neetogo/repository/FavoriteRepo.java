package com.jiwon.neetogo.repository;


import com.jiwon.neetogo.entity.Favorite;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface FavoriteRepo extends CrudRepository<Favorite, Long> {
    List<Favorite> findAll();
}
