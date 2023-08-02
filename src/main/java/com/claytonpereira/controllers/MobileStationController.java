package com.claytonpereira.controllers;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MobileStationController {
    @GetMapping("/location/{uuid}")
    public ResponseEntity<?> getMobileStationLocation(@PathVariable String uuid) {
        return new ResponseEntity<>(200, HttpStatus.NOT_IMPLEMENTED);
    }
}