package com.claytonpereira.controllers;

import com.claytonpereira.models.ApiResponseModel;
import com.claytonpereira.services.MobileStationService;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class MobileStationControllerTest {
    @Mock
    private MobileStationService mobileStationService;

    @InjectMocks
    private MobileStationController mobileStationController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(mobileStationController).build();
    }

    @Test
    public void testGetMobileStationLocation() throws Exception {
        // Prepare test data
        String uuid = "example-uuid";
        String locationResponse = "{\"latitude\": 1.23456, \"longitude\": 7.89012}";

        // Prepare the response from the service
        ApiResponseModel apiResponseModel = new ApiResponseModel();
        ApiResponseModel.ApiStatusAndMessage statusAndMessage = new ApiResponseModel.ApiStatusAndMessage();
        statusAndMessage.setStatus(200);
        statusAndMessage.setMessage("Location retrieved successfully");
        apiResponseModel.setResponseInformation(statusAndMessage);
        String responseJson = new Gson().toJson(apiResponseModel);

        // Mock the behavior of the mobileStationService
        when(mobileStationService.getMobileStationByUUID(anyString())).thenReturn(responseJson);

        // Perform the GET request and validate the response
        mockMvc.perform(get("/location/{uuid}", uuid))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.responseInformation.status").value(200))
                .andExpect(jsonPath("$.responseInformation.message").value("Location retrieved successfully"));
    }
}