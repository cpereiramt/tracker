package com.claytonpereira.models;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = com.claytonpereira.models.MobileStation.class)
public class BaseStationTests {
    @Test
    public void testAddBaseStation() {
        BaseStation baseStation = new BaseStation();
        baseStation.setId("bs1");
        baseStation.setName("Base Station 1");
        baseStation.setX(10.0F);
        baseStation.setY(20.0F);
        baseStation.setDetectionRadiusInMeters(100.0F);

        assertEquals("bs1", baseStation.getId());
        assertEquals("Base Station 1", baseStation.getName());
        assertEquals(10.0, baseStation.getX(), 0.001);
        assertEquals(20.0, baseStation.getY(), 0.001);
        assertEquals(100.0, baseStation.getDetectionRadiusInMeters(), 0.001);
    }
}