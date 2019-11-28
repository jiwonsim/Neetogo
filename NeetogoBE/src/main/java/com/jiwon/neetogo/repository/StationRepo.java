package com.jiwon.neetogo.repository;

import com.jiwon.neetogo.entity.StationEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StationRepo extends CrudRepository<StationEntity, Long> {
    @Query(
            value = "SELECT * FROM station s WHERE s.station_nm LIKE %?1%",
            nativeQuery = true
    )
    List<StationEntity> findByStationNm(String stationNm);


}
