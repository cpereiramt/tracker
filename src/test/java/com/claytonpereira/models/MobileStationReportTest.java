package com.claytonpereira.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MobileStationReportTest {

    private MobileStationReport mobileStationReport;

    @BeforeEach
    public void setUp() {
        mobileStationReport = new MobileStationReport();
    }

    @Test
    public void testConstructor() {
        assertEquals(null, mobileStationReport.getMobileStationId());
        assertEquals(0.0, mobileStationReport.getDistance());
        assertEquals(null, mobileStationReport.getTimestamp());
    }

    @Test
    public void testGettersAndSetters() {
        mobileStationReport.setMobileStationId("MS-001");
        mobileStationReport.setDistance(10.5f);

        assertEquals("MS-001", mobileStationReport.getMobileStationId());
        assertEquals(10.5f, mobileStationReport.getDistance());
    }

    @Test
    public void testEqualsAndHashCode() {
        MobileStationReport report1 = new MobileStationReport();
        report1.setMobileStationId("MS-001");

        MobileStationReport report2 = new MobileStationReport();
        report2.setMobileStationId("MS-001");

        MobileStationReport report3 = new MobileStationReport();
        report3.setMobileStationId("MS-002");

        assertEquals(report1, report2);
        assertNotEquals(report1, report3);
        assertEquals(report1.hashCode(), report2.hashCode());
    }
}