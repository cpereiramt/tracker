package com.claytonpereira.services;

import com.claytonpereira.models.BaseStation;
import com.claytonpereira.models.BaseStationReport;
import com.claytonpereira.models.MobileStation;
import com.claytonpereira.models.MobileStationReport;
import com.claytonpereira.repositories.BaseStationReportRepository;
import com.claytonpereira.repositories.BaseStationRepository;
import com.claytonpereira.repositories.MobileStationRepository;
import com.claytonpereira.utils.CalculateDistanceBetweenMSToBS;
import com.claytonpereira.utils.GenerateBaseAndMobileStationID;
import com.claytonpereira.utils.MobileStationPositionFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class BaseStationMonitorService {
    @Value("${rest.endpoint1.url}")
    String endpoint1;
    private final MobileStationRepository mobileStationRepository;
    private final BaseStationRepository baseStationRepository;

    private BaseStationReportRepository baseStationReportRepository;
    private MobileStationPositionFetcher positionFetcher;
    private RestTemplate restTemplate;
    @Autowired
    public BaseStationMonitorService(MobileStationRepository mobileStationRepository, BaseStationRepository baseStationRepository, MobileStationPositionFetcher positionFetcher, RestTemplate restTemplate, BaseStationReportRepository baseStationReportRepository) {
       this.baseStationRepository = baseStationRepository;
       this.mobileStationRepository = mobileStationRepository;
       this.positionFetcher = positionFetcher;
       this.restTemplate = restTemplate;
       this.baseStationReportRepository = baseStationReportRepository;
   }
    @Scheduled(fixedDelay = 5000)
    public void monitorBaseStations() {
        System.out.println("Scheduled starting executing ..............");
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
                boolean alrealdyExistMobile = baseStationReportRepository.existsByBaseStationIdAndMobileStationId(baseStation.getId(), mobileStationID);
                if (distance <= baseStation.getDetectionRadiusInMeters() && !alrealdyExistMobile) {
                    mobileStationReport.setDistance(distance);
                   mobileStationReport.setMobileStationId(mobileStationFromResponse.getMobileId());
                    mobileStationReport.setTimestamp(new Timestamp(System.currentTimeMillis()));
                    detectedMobileStations.add(mobileStationReport);
                }
            }
           if(detectedMobileStations.size() > 0) {
               baseStationReport.setReports(detectedMobileStations);
               HttpHeaders headers = new HttpHeaders();
               headers.setContentType(MediaType.APPLICATION_JSON);
               HttpEntity<BaseStationReport> request = new HttpEntity<>(baseStationReport, headers);
               restTemplate.postForEntity(endpoint1, request, String.class);
               List<String> mobileIDSSavedInReport = baseStationReport.getReports().stream().map(mobileStationReport -> mobileStationReport.getMobileStationId()).collect(Collectors.toList());

               System.out.println("mobiles Station " + mobileIDSSavedInReport.toString() + " from Base ID " + baseStationReport.getBaseStationId() + " saved ");
           }
        }
    }
}