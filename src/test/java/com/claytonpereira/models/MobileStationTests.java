package com.claytonpereira.models;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = com.claytonpereira.models.MobileStation.class)
public class MobileStationTests {

    @Test
    public void testAddMobileStation() {
        MobileStation mobileStation = new MobileStation();
        mobileStation.setMobileId("m1");
        mobileStation.setLastKnownY(40.0F);
        mobileStation.setLastKnownX(30.0F);
        assertEquals("m1", mobileStation.getMobileId());
        assertEquals(30.0, mobileStation.getLastKnownX(), 0.001);
        assertEquals(40.0, mobileStation.getLastKnownY(), 0.001);
    }
}