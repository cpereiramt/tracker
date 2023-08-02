package com.claytonpereira.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class BaseStation {
    @Id
    private String id;
    private String name;
    private float x;
    private float y;
    private float detectionRadiusInMeters;
}