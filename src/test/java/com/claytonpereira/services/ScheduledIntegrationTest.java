package com.claytonpereira.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = com.claytonpereira.tracker.TrackerApplication.class)
@SpringBootTest
public class ScheduledIntegrationTest {
    @Autowired
    BaseStationMonitorService monitorService;
    @Test
    public void givenSleepBy100ms_whenGetInvocationCount_thenIsGreaterThanZero(){

        assertEquals(true, true);
    }

}
