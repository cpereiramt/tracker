package com.claytonpereira.repositories;

import com.claytonpereira.models.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BaseStationReportRepository extends JpaRepository<Report, String> {
    // Verifica se existe um registro com a combinação de baseStationId e mobileStationId
    @Query(value = "SELECT COUNT(*) > 0 FROM report r WHERE r.base_station_id = ?1 AND r.mobile_station_id = ?2", nativeQuery = true)
    boolean existsByBaseStationIdAndMobileStationId(String baseStationId, String mobileStationId);
}
