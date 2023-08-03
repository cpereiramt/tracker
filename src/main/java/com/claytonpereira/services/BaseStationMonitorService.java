package com.claytonpereira.services;

import com.claytonpereira.models.*;
import com.claytonpereira.repositories.BaseStationRepository;
import com.claytonpereira.repositories.MobileStationRepository;
import com.claytonpereira.utils.CalculateDistanceBetweenMSToBS;
import com.claytonpereira.utils.GenerateBaseAndMobileStationID;
import com.claytonpereira.utils.MobileStationPositionFetcher;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Component
public class BaseStationMonitorService {
    @Value("${rest.endpoint1.url}")
    String endpoint1;
    Gson jsonParser = new Gson();
    private MobileStationRepository mobileStationRepository;
    private BaseStationRepository baseStationRepository;
    private MobileStationPositionFetcher positionFetcher;
    private RestTemplate restTemplate;
    public BaseStationMonitorService(MobileStationRepository mobileStationRepository, BaseStationRepository baseStationRepository, MobileStationPositionFetcher positionFetcher, RestTemplate restTemplate) {
       this.baseStationRepository = baseStationRepository;
       this.mobileStationRepository = mobileStationRepository;
       this.positionFetcher = positionFetcher;
       this.restTemplate = restTemplate;

   }
    @Scheduled(fixedDelay = 5000)
    public void monitorBaseStations() {
        long mobileIDSCount = mobileStationRepository.count();
        List<String> mobileIDS = GenerateBaseAndMobileStationID.generateMobileStationID(mobileIDSCount);
        List<BaseStation> baseStationList =  baseStationRepository.findAll();

        for (BaseStation baseStation : baseStationList) {
            BaseStationReport baseStationReport = new BaseStationReport();
            List<MobileStationReport> detectedMobileStations = new ArrayList<>();
            baseStationReport.setBaseStationId(baseStation.getId());
            for (String mobileStationID : mobileIDS) {
                MobileStation mobileStationFromResponse = positionFetcher.fetchMobileStationPosition(mobileStationID);
                MobileStationReport mobileStationReport = new MobileStationReport();
                double latBaseRadians = Math.toRadians(baseStation.getX());
                double lonBaseRadians = Math.toRadians(baseStation.getY());
                double latMobRadians = Math.toRadians(mobileStationFromResponse.getLastKnownX());
                double lonMobRadians = Math.toRadians(mobileStationFromResponse.getLastKnownY());
              float distance = (float) CalculateDistanceBetweenMSToBS.calculateDistance(latBaseRadians, lonBaseRadians,latMobRadians,lonMobRadians);
               if (distance <= baseStation.getDetectionRadiusInMeters()) {
                    mobileStationReport.setDistance(distance);
                   mobileStationReport.setMobileStationId(mobileStationFromResponse.getMobileId());
                    mobileStationReport.setTimestamp(new Timestamp(System.currentTimeMillis()));
                    detectedMobileStations.add(mobileStationReport);
                }
            }
           if(detectedMobileStations.size() > 0){
                baseStationReport.setReports(detectedMobileStations);
            }
           else {
               baseStationReport = null;
           }
           if (baseStationReport != null) {
               HttpHeaders headers = new HttpHeaders();
               headers.setContentType(MediaType.APPLICATION_JSON);
               HttpEntity<BaseStationReport> request = new HttpEntity<>(baseStationReport, headers);
               restTemplate.postForEntity(endpoint1, request, String.class);
               System.out.println("report from Base ID saved " + baseStationReport.getBaseStationId());
           }
        }
    }
}