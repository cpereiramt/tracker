package com.claytonpereira.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class MobileStation {
    @Id
    @Column(name = "mobile_id")
    private String mobileId;
    private float lastKnownX;
    private float lastKnownY;

    @OneToMany(mappedBy = "baseStation")
    private List<Report> reports = new ArrayList<>();
}