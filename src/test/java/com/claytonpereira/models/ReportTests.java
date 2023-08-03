package com.claytonpereira.models;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReportTests {
    @Test
    public void testAddReport() {
        MobileStation mobileStation = new MobileStation();
        mobileStation.setMobileId("m1");
        mobileStation.setLastKnownY(40.0F);
        mobileStation.setLastKnownX(30.0F);
        BaseStation baseStation = new BaseStation();
        baseStation.setId("bs1");
        baseStation.setName("Base Station 1");
        baseStation.setX(10.0F);
        baseStation.setY(20.0F);
        baseStation.setDetectionRadiusInMeters(100.0F);
        Report report = new Report();
        report.setBaseStation(baseStation);
        report.setMobileStation(mobileStation);
        report.setDistance(50.0F);
        report.setTimestamp(new Timestamp(System.currentTimeMillis()));
        assertEquals(baseStation, report.getBaseStation());
        assertEquals(mobileStation, report.getMobileStation());
        assertEquals(50.0, report.getDistance(), 0.001);
    }
}
