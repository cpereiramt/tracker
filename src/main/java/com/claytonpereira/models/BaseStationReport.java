package com.claytonpereira.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BaseStationReport {
    private String baseStationId;
    private List<MobileStationReport> reports;

}