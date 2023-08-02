package com.claytonpereira.controllers;

import com.claytonpereira.models.ApiResponseModel;
import com.claytonpereira.services.MobileStationService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MobileStationController {
    private final MobileStationService mobileStationService;
    Gson jsonParser = new Gson();
    @Autowired
    public MobileStationController(MobileStationService mobileStationService) {
        this.mobileStationService = mobileStationService;
    }
    @GetMapping("/location/{uuid}")
    public ResponseEntity<?> getMobileStationLocation(@PathVariable String uuid) {
        String response =   mobileStationService.getMobileStationByUUID(uuid);
        ApiResponseModel newApiResponseModel = jsonParser.fromJson(response,ApiResponseModel.class);
        ApiResponseModel.ApiStatusAndMessage statusAndMessage = newApiResponseModel.getResponseInformation();
        return ResponseEntity.status(statusAndMessage.getStatus()).body(response);
    }
}