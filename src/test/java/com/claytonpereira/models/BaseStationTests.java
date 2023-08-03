package com.claytonpereira.tracker.models;

import static org.junit.Assert.assertEquals;

import com.claytonpereira.models.BaseStation;
import org.junit.Test;

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