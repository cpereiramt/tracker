package com.claytonpereira.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@EqualsAndHashCode
public class BaseStationReport {

    private Long id;
    private String baseStationId;
    private List<MobileStationReport> reports = new ArrayList<>();
}
