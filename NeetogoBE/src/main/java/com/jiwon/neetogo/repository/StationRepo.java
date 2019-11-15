package com.jiwon.neetogo.repository;

import com.jiwon.neetogo.entity.Station;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface StationRepo extends CrudRepository<Station, Long> {
    @Query(
            value = "SELECT * FROM station s WHERE s.station_nm LIKE %?1%",
            nativeQuery = true
    )
    List<Station> findByStationNm(String stationNm);


}
