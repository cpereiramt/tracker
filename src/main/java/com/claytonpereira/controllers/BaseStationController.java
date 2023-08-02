package com.claytonpereira.controllers;

import com.claytonpereira.models.ApiResponseModel;
import com.claytonpereira.models.BaseStationReport;
import com.claytonpereira.services.BaseStationService;
import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseStationController {
    Gson jsonParser = new Gson();
    private final BaseStationService baseStationService;

    public BaseStationController(BaseStationService baseStationService) {
        this.baseStationService = baseStationService;
    }
    @PostMapping("/reports")
    public ResponseEntity<?> receiveReports(@RequestBody BaseStationReport report) {
        String response =  baseStationService.saveReports(report);;
        ApiResponseModel newApiResponseModel = jsonParser.fromJson(response,ApiResponseModel.class);
        ApiResponseModel.ApiStatusAndMessage statusAndMessage = newApiResponseModel.getResponseInformation();
        return ResponseEntity.status(statusAndMessage.getStatus()).body(response);
    }
}