package com.claytonpereira.models;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class MobileStation {
    @Id
    private String mobileId;
    private float lastKnownX;
    private float lastKnownY;
}