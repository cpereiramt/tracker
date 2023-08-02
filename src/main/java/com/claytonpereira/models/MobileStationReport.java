package com.claytonpereira.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;

@Getter
@Setter
@Entity
public class MobileStationReport {
    @Id
    @Column(name = "mobile_station_id")
    private String mobileStationId;
    private float distance;
    private Timestamp timestamp;
}