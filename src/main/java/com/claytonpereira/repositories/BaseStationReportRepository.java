package com.claytonpereira.repositories;

import com.claytonpereira.models.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseStationReportRepository extends JpaRepository<Report, String> {
}
