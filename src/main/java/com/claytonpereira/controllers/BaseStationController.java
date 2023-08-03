package com.claytonpereira.controllers;

import com.claytonpereira.models.ApiResponseModel;
import com.claytonpereira.models.BaseStationReport;
import com.claytonpereira.services.BaseStationService;
import com.google.gson.Gson;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(summary = "Register a new report", description = "Create a new report that associate on BaseStation to a list of MobileStations.")
    @ApiResponse(responseCode = "201", description = "Data inserted successfully.")
    @ApiResponse(responseCode = "500", description = "Internal Server Error.")
    public ResponseEntity<?> receiveReports(@RequestBody BaseStationReport report) {
        String response =  baseStationService.saveReports(report);;
        ApiResponseModel newApiResponseModel = jsonParser.fromJson(response,ApiResponseModel.class);
        ApiResponseModel.ApiStatusAndMessage statusAndMessage = newApiResponseModel.getResponseInformation();
        return ResponseEntity.status(statusAndMessage.getStatus()).body(response);
    }
}
