package com.claytonpereira.models;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class MobileStationReport {
    private String mobileStationId;
    private float distance;
    private Instant timestamp;

    // Construtores, getters e setters
}