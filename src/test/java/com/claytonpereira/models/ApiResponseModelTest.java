package com.claytonpereira.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ApiResponseModelTest {

    private ApiResponseModel<MobileStation> apiResponseModel;

    @BeforeEach
    public void setUp() {
        apiResponseModel = new ApiResponseModel<>();

    }

    @Test
    public void testConstructor() {
        assertEquals(false, apiResponseModel.isSuccess());
        assertEquals(null, apiResponseModel.getData());
        assertNotNull(apiResponseModel.getResponseInformation());
        assertEquals(0, apiResponseModel.getResponseInformation().getStatus());
        assertEquals(null, apiResponseModel.getResponseInformation().getMessage());
    }

    @Test
    public void testGettersAndSetters() {
        MobileStation mobileStation = new MobileStation();
        mobileStation.setMobileId("m1");
        mobileStation.setLastKnownY(40.0F);
        mobileStation.setLastKnownX(30.0F);
        apiResponseModel.setSuccess(true);
        apiResponseModel.setData(mobileStation);

        assertEquals(true, apiResponseModel.isSuccess());
        assertEquals(mobileStation, apiResponseModel.getData());
    }

    @Test
    public void testApiStatusAndMessage() {
        ApiResponseModel.ApiStatusAndMessage statusAndMessage = new ApiResponseModel.ApiStatusAndMessage();
        statusAndMessage.setStatus(200);
        statusAndMessage.setMessage("OK");

        assertEquals(200, statusAndMessage.getStatus());
        assertEquals("OK", statusAndMessage.getMessage());
    }

}