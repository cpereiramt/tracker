package com.claytonpereira.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MobileStation {
    private String id;
    private float lastKnownX;
    private float lastKnownY;
}