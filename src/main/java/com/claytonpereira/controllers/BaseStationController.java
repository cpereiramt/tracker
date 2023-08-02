package com.claytonpereira.controllers;

import com.claytonpereira.models.BaseStationReport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseStationController {
    @PostMapping("/reports")
    public ResponseEntity<?> receiveReports(@RequestBody BaseStationReport report) {
        return new ResponseEntity<>(200, HttpStatus.NOT_IMPLEMENTED);
    }
}