package com.claytonpereira.services;

import com.claytonpereira.models.BaseStation;
import com.claytonpereira.models.MobileStation;
import com.claytonpereira.repositories.BaseStationReportRepository;
import com.claytonpereira.repositories.BaseStationRepository;
import com.claytonpereira.repositories.MobileStationRepository;
import com.claytonpereira.utils.MobileStationPositionFetcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BaseStationMonitorServiceTest {

    @Mock
    private MobileStationRepository mobileStationRepository;

    @Mock
    private BaseStationRepository baseStationRepository;

    @Mock
    private MobileStationPositionFetcher positionFetcher;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private BaseStationReportRepository baseStationReportRepository;

    @InjectMocks
    private BaseStationMonitorService baseStationMonitorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMonitorBaseStations() {

        BaseStation baseStation1 = new BaseStation();
        baseStation1.setId("bs1");
        baseStation1.setName("Base Station 1");
        baseStation1.setX(10.0F);
        baseStation1.setY(20.0F);
        baseStation1.setDetectionRadiusInMeters(100.0F);

        BaseStation baseStation2 = new BaseStation();
        baseStation2.setId("bs1");
        baseStation2.setName("Base Station 2");
        baseStation2.setX(40.0F);
        baseStation2.setY(50.0F);
        baseStation2.setDetectionRadiusInMeters(180.0F);


        MobileStation mobileStation1 = new MobileStation();
        mobileStation1.setMobileId("m1");
        mobileStation1.setLastKnownY(40.0F);
        mobileStation1.setLastKnownX(30.0F);

        MobileStation mobileStation2 = new MobileStation();
        mobileStation2.setMobileId("m2");
        mobileStation2.setLastKnownY(90.0F);
        mobileStation2.setLastKnownX(50.0F);

        when(mobileStationRepository.count()).thenReturn(1L);
        when(baseStationRepository.findAll()).thenReturn(Collections.singletonList(new BaseStation()));
        when(positionFetcher.fetchMobileStationPosition(mobileStation1.getMobileId()))
                .thenReturn(mobileStation1);
        when(positionFetcher.fetchMobileStationPosition(mobileStation2.getMobileId()))
                .thenReturn(mobileStation2);
        baseStationMonitorService.monitorBaseStations();
        verify(mobileStationRepository).count();
        verify(baseStationRepository).findAll();
        verify(positionFetcher).fetchMobileStationPosition("m1");
    }
}