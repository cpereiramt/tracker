package com.claytonpereira.utils;

import com.claytonpereira.models.ApiResponseModel;
import com.claytonpereira.models.MobileStation;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class MobileStationPositionFetcher {

    private final RestTemplate restTemplate;
    private final String endpointUrl;
    Gson jsonParser = new Gson();
    @Autowired
    public MobileStationPositionFetcher(RestTemplate restTemplate, @Value("${rest.endpoint2.url}") String endpointUrl) {
        this.restTemplate = restTemplate;
        this.endpointUrl = endpointUrl;
    }

    public MobileStation fetchMobileStationPosition(String mobileId) {
        String requestUrl = endpointUrl + mobileId;
        try {
            ParameterizedTypeReference<ApiResponseModel<MobileStation>> responseType =
                new ParameterizedTypeReference<ApiResponseModel<MobileStation>>() {};
            ResponseEntity<ApiResponseModel<MobileStation>> responseEntity =
                 restTemplate.exchange(requestUrl, HttpMethod.GET, null, responseType);
            MobileStation responseData = responseEntity.getBody().getData();
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return responseData;
            } else {
                return null;
            }
        } catch (HttpClientErrorException.NotFound ex) {
        // Handle the 404 error here
            System.out.println("Mobile station with ID " + mobileId + " not found.");
            return null;
        } catch (HttpClientErrorException ex) {
        // Handle other client errors if needed
            System.out.println("Error occurred: " + ex.getStatusCode() + " - " + ex.getStatusText());
            return null;
        } catch (Exception ex) {
        // Handle other exceptions if needed
            ex.printStackTrace();
            return null;
        }
    }
}
