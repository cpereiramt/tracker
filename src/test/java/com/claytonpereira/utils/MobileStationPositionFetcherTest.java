package com.claytonpereira.utils;

import com.claytonpereira.models.ApiResponseModel;
import com.claytonpereira.models.MobileStation;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MobileStationPositionFetcherTest {

    @Test
    public void testFetchMobileStationPosition_Success() {
        // Arrange
        RestTemplate restTemplateMock = mock(RestTemplate.class);
        String endpointUrl = "http://example.com/mobiles/";
        MobileStationPositionFetcher mobileStationPositionFetcher = new MobileStationPositionFetcher(restTemplateMock, endpointUrl);

        String mobileId = "12345";
        String requestUrl = endpointUrl + mobileId;

        MobileStation mobileStationData = new MobileStation();
        ApiResponseModel<MobileStation> apiResponse = new ApiResponseModel<>();
        apiResponse.setSuccess(true);
        apiResponse.setData(mobileStationData);
        ResponseEntity<ApiResponseModel<MobileStation>> responseEntity = new ResponseEntity<>(apiResponse, HttpStatus.OK);

        when(restTemplateMock.exchange(requestUrl, HttpMethod.GET, null, new ParameterizedTypeReference<ApiResponseModel<MobileStation>>(){}))
                .thenReturn(responseEntity);

        // Act
        MobileStation result = mobileStationPositionFetcher.fetchMobileStationPosition(mobileId);

        // Assert
        assertEquals(mobileStationData, result);
    }

    @Test
    public void testFetchMobileStationPosition_NotFound() {
        // Arrange
        RestTemplate restTemplateMock = mock(RestTemplate.class);
        String endpointUrl = "http://example.com/mobiles/";
        MobileStationPositionFetcher mobileStationPositionFetcher = new MobileStationPositionFetcher(restTemplateMock, endpointUrl);

        String mobileId = "12345";
        String requestUrl = endpointUrl + mobileId;

        HttpClientErrorException ex = new HttpClientErrorException(HttpStatus.NOT_FOUND, "Mobile not found");
        when(restTemplateMock.exchange(requestUrl, HttpMethod.GET, null, new ParameterizedTypeReference<ApiResponseModel<MobileStation>>(){}))
                .thenThrow(ex);

        MobileStation result = mobileStationPositionFetcher.fetchMobileStationPosition(mobileId);

        assertNull(result);
    }
}