package com.jiwon.neetogo.repository;

import com.jiwon.neetogo.dto.FavoriteDTO;
import com.jiwon.neetogo.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends CrudRepository<Favorite, Integer> {
    List<Favorite> findAll();
}
