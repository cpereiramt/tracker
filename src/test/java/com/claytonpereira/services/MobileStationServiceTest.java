package com.claytonpereira.services;

import com.claytonpereira.models.ApiResponseModel;
import com.claytonpereira.models.MobileStation;
import com.claytonpereira.repositories.MobileStationRepository;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class MobileStationServiceTest {

    @Mock
    private MobileStationRepository mobileStationRepository;

    @InjectMocks
    private MobileStationService mobileStationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetMobileStationByUUID_ExistingUUID() {
        String uuid = "mobile-station-uuid";
        MobileStation mobileStation = new MobileStation();
        mobileStation.setMobileId(uuid);
        mobileStation.setLastKnownX(12.345F);
        mobileStation.setLastKnownX(67.890F);

        when(mobileStationRepository.findById(uuid)).thenReturn(Optional.of(mobileStation));
        String responseEntity = mobileStationService.getMobileStationByUUID(uuid);
        Gson jsonParse = new Gson();
        ApiResponseModel apiResponseModel = jsonParse.fromJson(responseEntity, ApiResponseModel.class);
        apiResponseModel.getResponseInformation().getStatus();
         assertEquals(apiResponseModel.getResponseInformation().getStatus(), 200);
    }

}