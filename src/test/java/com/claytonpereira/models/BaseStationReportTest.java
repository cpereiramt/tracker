package com.claytonpereira.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class BaseStationReportTest {

    private BaseStationReport baseStationReport;

    @BeforeEach
    public void setUp() {
        baseStationReport = new BaseStationReport();
    }

    @Test
    public void testConstructor() {
        assertEquals(null, baseStationReport.getId());
        assertEquals(null, baseStationReport.getBaseStationId());
    }

    @Test
    public void testGettersAndSetters() {
        baseStationReport.setId(1L);
        baseStationReport.setBaseStationId("BS-01");

        assertEquals(1L, baseStationReport.getId());
        assertEquals("BS-01", baseStationReport.getBaseStationId());
    }

    @Test
    public void testAddReports() {
        MobileStationReport report1 = new MobileStationReport();
        MobileStationReport report2 = new MobileStationReport();
        List<MobileStationReport> addReports = baseStationReport.getReports();
        addReports.add(report1);
        addReports.add(report2);
        baseStationReport.setReports(addReports);

        List<MobileStationReport> reports = baseStationReport.getReports();
        assertEquals(2, reports.size());
        assertEquals(report1, reports.get(0));
        assertEquals(report2, reports.get(1));
    }

    @Test
    public void testRemoveReports() {
        MobileStationReport report1 = new MobileStationReport();
        MobileStationReport report2 = new MobileStationReport();
        List<MobileStationReport> removeReports = baseStationReport.getReports();
        removeReports.add(report1);
        removeReports.add(report2);
        baseStationReport.setReports(removeReports);
        removeReports.remove(report1);

        List<MobileStationReport> reports = baseStationReport.getReports();
        assertEquals(1, reports.size());
        assertEquals(report2, reports.get(0));
    }

    @Test
    public void testEqualsAndHashCode() {
        BaseStationReport report1 = new BaseStationReport();
        report1.setId(1L);
        report1.setBaseStationId("BS-01");

        BaseStationReport report2 = new BaseStationReport();
        report2.setId(1L);
        report2.setBaseStationId("BS-01");

        BaseStationReport report3 = new BaseStationReport();
        report3.setId(2L);
        report3.setBaseStationId("BS-02");

        assertEquals(report1, report2);
        assertNotEquals(report1, report3);
        assertEquals(report1.hashCode(), report2.hashCode());
    }
}