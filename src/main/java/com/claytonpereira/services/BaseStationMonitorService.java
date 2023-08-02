package com.claytonpereira.services;

import com.claytonpereira.models.*;
import com.claytonpereira.repositories.BaseStationRepository;
import com.claytonpereira.repositories.MobileStationRepository;
import com.claytonpereira.utils.CalculateDistanceBetweenMSToBS;
import com.google.gson.Gson;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Component
public class BaseStationMonitorService {
    Gson jsonParser = new Gson();

    private MobileStationRepository mobileStationRepository;
    private BaseStationRepository baseStationRepository;
    // Your data structures or database references for base stations and mobile stations
   public BaseStationMonitorService(MobileStationRepository mobileStationRepository, BaseStationRepository baseStationRepository ) {
       this.baseStationRepository = baseStationRepository;
       this.mobileStationRepository = mobileStationRepository;

   }
    @Scheduled(fixedDelay = 8000)
    public void monitorBaseStations() {
        List<BaseStation> baseStationList =  baseStationRepository.findAll();
        List<MobileStation> mobileStationList =  mobileStationRepository.findAll();

        for (BaseStation baseStation : baseStationList) {
            BaseStationReport baseStationReport = new BaseStationReport();
            List<MobileStationReport> detectedMobileStations = new ArrayList<>();
            baseStationReport.setBaseStationId(baseStation.getId());
            for (MobileStation mobileStation : mobileStationList) {
                MobileStationReport mobileStationReport = new MobileStationReport();
                double latBaseRadians = Math.toRadians(baseStation.getX());
                double lonBaseRadians = Math.toRadians(baseStation.getY());
                double latMobRadians = Math.toRadians(mobileStation.getLastKnownX());
                double lonMobRadians = Math.toRadians(mobileStation.getLastKnownY());
                float distance = (float) CalculateDistanceBetweenMSToBS.calculateDistance(latBaseRadians, lonBaseRadians,latMobRadians,lonMobRadians);
                if (distance <= baseStation.getDetectionRadiusInMeters()) {
                    mobileStationReport.setDistance(distance);
                    mobileStationReport.setMobileStationId(mobileStation.getMobileId());
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
               String jsonObject = jsonParser.toJson(baseStationReport);
               System.out.println("Each Base Object --->" + jsonObject);
           }
        }
    }
}