package com.claytonpereira.repositories;

import com.claytonpereira.models.BaseStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseStationRepository extends JpaRepository<BaseStation, String> {

}