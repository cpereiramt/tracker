package com.claytonpereira.services;

import com.claytonpereira.models.*;
import com.claytonpereira.repositories.BaseStationReportRepository;
import com.claytonpereira.repositories.BaseStationRepository;
import com.claytonpereira.repositories.MobileStationRepository;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BaseStationServiceTest {

    @Mock
    private BaseStationReportRepository baseStationReportRepository;

    @Mock
    private MobileStationRepository mobileStationRepository;

    @Mock
    private BaseStationRepository baseStationRepository;

    @InjectMocks
    private BaseStationService baseStationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveReports_SuccessfulInsert() {
        BaseStationReport baseStationReport = new BaseStationReport();
        baseStationReport.setBaseStationId("base-station-id");

        MobileStationReport mobileStationReport1 = new MobileStationReport();
        mobileStationReport1.setMobileStationId("mobile-station-id-1");
        mobileStationReport1.setTimestamp(new Timestamp(System.currentTimeMillis()));
        mobileStationReport1.setDistance(10.5F);

        MobileStationReport mobileStationReport2 = new MobileStationReport();
        mobileStationReport2.setMobileStationId("mobile-station-id-2");
        mobileStationReport2.setTimestamp(new Timestamp(System.currentTimeMillis()));
        mobileStationReport2.setDistance(20.3F);

        List<MobileStationReport> mobileStationReports = new ArrayList<>();
        mobileStationReports.add(mobileStationReport1);
        mobileStationReports.add(mobileStationReport2);

        baseStationReport.setReports(mobileStationReports);

        BaseStation baseStation = new BaseStation();
        baseStation.setId("base-station-id");

        MobileStation mobileStation1 = new MobileStation();
        mobileStation1.setMobileId("mobile-station-id-1");

        MobileStation mobileStation2 = new MobileStation();
        mobileStation2.setMobileId("mobile-station-id-2");

        when(baseStationRepository.findById("base-station-id")).thenReturn(Optional.of(baseStation));
        when(mobileStationRepository.findById("mobile-station-id-1")).thenReturn(Optional.of(mobileStation1));
        when(mobileStationRepository.findById("mobile-station-id-2")).thenReturn(Optional.of(mobileStation2));
        when(baseStationReportRepository.existsByBaseStationIdAndMobileStationId(any(), any())).thenReturn(false);

        String jsonResponse = baseStationService.saveReports(baseStationReport);

        ApiResponseModel<BaseStationReport> apiResponseModel = new Gson().fromJson(jsonResponse, ApiResponseModel.class);
        ApiResponseModel.ApiStatusAndMessage statusAndMessage = apiResponseModel.getResponseInformation();
        assertEquals(201, statusAndMessage.getStatus());
        assertEquals("Data inserted successfully", statusAndMessage.getMessage());
        verify(baseStationReportRepository, times(2)).save(any());
    }

    @Test
    public void testSaveReports_DuplicateData() {
        BaseStationReport baseStationReport = new BaseStationReport();
        baseStationReport.setBaseStationId("base-station-id");

        MobileStationReport mobileStationReport = new MobileStationReport();
        mobileStationReport.setMobileStationId("mobile-station-id");
        mobileStationReport.setTimestamp(new Timestamp(System.currentTimeMillis()));
        mobileStationReport.setDistance(10.5F);

        List<MobileStationReport> mobileStationReports = new ArrayList<>();
        mobileStationReports.add(mobileStationReport);

        baseStationReport.setReports(mobileStationReports);

        BaseStation baseStation = new BaseStation();
        baseStation.setId("base-station-id");

        MobileStation mobileStation = new MobileStation();
        mobileStation.setMobileId("mobile-station-id");

        when(baseStationRepository.findById("base-station-id")).thenReturn(Optional.of(baseStation));
        when(mobileStationRepository.findById("mobile-station-id")).thenReturn(Optional.of(mobileStation));
        when(baseStationReportRepository.existsByBaseStationIdAndMobileStationId(any(), any())).thenReturn(true);

        String jsonResponse = baseStationService.saveReports(baseStationReport);
        String jsonResponse2 = baseStationService.saveReports(baseStationReport);
        ApiResponseModel<BaseStationReport> apiResponseModel = new Gson().fromJson(jsonResponse2, ApiResponseModel.class);
        ApiResponseModel.ApiStatusAndMessage statusAndMessage = apiResponseModel.getResponseInformation();
        assertEquals(201, statusAndMessage.getStatus());
        assertEquals("Data inserted successfully with the follow mobileID Not Inserted [mobile-station-id]", statusAndMessage.getMessage());
        verify(baseStationReportRepository, never()).save(any());
    }
}