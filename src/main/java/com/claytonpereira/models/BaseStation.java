package com.claytonpereira.models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "mobileStation")
    private List<Report> reports = new ArrayList<>();
}