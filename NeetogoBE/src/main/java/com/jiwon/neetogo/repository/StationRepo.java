package com.jiwon.neetogo.repository;

import com.jiwon.neetogo.entity.Station;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface StationRepo extends CrudRepository<Station, Long> {
}
