package com.claytonpereira.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class MobileStationReport {

    private String mobileStationId;
    private float distance;
    private Timestamp timestamp;
}