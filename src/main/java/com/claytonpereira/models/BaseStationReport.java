package com.claytonpereira.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
public class BaseStationReport {

    private Long id;


    private String baseStationId;


    private List<MobileStationReport> reports;
}