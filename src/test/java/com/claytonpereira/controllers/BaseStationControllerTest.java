package com.claytonpereira.controllers;

import com.claytonpereira.models.ApiResponseModel;
import com.claytonpereira.models.BaseStation;
import com.claytonpereira.models.BaseStationReport;
import com.claytonpereira.models.MobileStationReport;
import com.claytonpereira.services.BaseStationService;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class BaseStationControllerTest {

    @Mock
    private BaseStationService baseStationService;

    @InjectMocks
    private BaseStationController baseStationController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(baseStationController).build();
    }

    @Test
    public void testReceiveReports() throws Exception {
        BaseStation baseStation = new BaseStation();
        baseStation.setId("bs1");
        baseStation.setName("Base Station 1");
        baseStation.setX(10.0F);
        baseStation.setY(20.0F);
        baseStation.setDetectionRadiusInMeters(100.0F);
        BaseStationReport report = new BaseStationReport();
        report.setId(1L);
        report.setBaseStationId(baseStation.getId());
        List<MobileStationReport> mobileStationList = new ArrayList<>();
        MobileStationReport mobileStation = new MobileStationReport();
        mobileStation.setMobileStationId("m1");
        mobileStation.setDistance(40.0F);
        mobileStation.setTimestamp(new Timestamp(System.currentTimeMillis()));
        mobileStationList.add(mobileStation);
        Gson gson = new Gson();
        String reportJson = gson.toJson(report);

        report.setReports(mobileStationList);
        ApiResponseModel apiResponseModel = new ApiResponseModel();
        ApiResponseModel.ApiStatusAndMessage statusAndMessage = new ApiResponseModel.ApiStatusAndMessage();
        statusAndMessage.setStatus(200);
        statusAndMessage.setMessage("Report saved successfully");
        apiResponseModel.setResponseInformation(statusAndMessage);
        String responseJson = gson.toJson(apiResponseModel);

        // Mock the behavior of the baseStationService
        when(baseStationService.saveReports(any(BaseStationReport.class))).thenReturn(responseJson);

        // Perform the POST request and validate the response
        mockMvc.perform(post("/reports")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reportJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.responseInformation.status").value(200))
                .andExpect(jsonPath("$.responseInformation.message").value("Report saved successfully"));
    }
}