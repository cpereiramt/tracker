package com.claytonpereira.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@EqualsAndHashCode

public class MobileStationReport {

    private String mobileStationId;
    private float distance;
    private Timestamp timestamp;
}