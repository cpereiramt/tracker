package com.claytonpereira.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseStation {
    private String id;
    private String name;
    private float x;
    private float y;
    private float detectionRadiusInMeters;
}