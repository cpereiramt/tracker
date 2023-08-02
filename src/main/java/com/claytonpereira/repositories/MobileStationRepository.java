package com.claytonpereira.repositories;

import com.claytonpereira.models.MobileStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MobileStationRepository extends JpaRepository<MobileStation, String> {

}
