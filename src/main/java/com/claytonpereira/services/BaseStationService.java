package com.claytonpereira.services;

import com.claytonpereira.models.*;
import com.claytonpereira.repositories.BaseStationReportRepository;
import com.claytonpereira.repositories.BaseStationRepository;
import com.claytonpereira.repositories.MobileStationRepository;
import com.claytonpereira.utils.ApiResponseConsts;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BaseStationService {

    private BaseStationReportRepository baseStationReportRepository;
    private MobileStationRepository mobileStationRepository;
    private BaseStationRepository baseStationRepository;

    String jsonResponse;
    Gson gson = new Gson();

    @Autowired
    public BaseStationService(MobileStationRepository mobileStationRepository, BaseStationRepository baseStationRepository, BaseStationReportRepository baseStationReportRepository) {
        this.mobileStationRepository = mobileStationRepository;
        this.baseStationRepository = baseStationRepository;
        this.baseStationReportRepository = baseStationReportRepository;
    }

    public String saveReports(BaseStationReport dataFromJson) {
        ApiResponseModel<BaseStationReport> response = new ApiResponseModel<>();
        ApiResponseModel.ApiStatusAndMessage apiStatusAndMessage = new ApiResponseModel.ApiStatusAndMessage();
        try {
            Optional<BaseStation> baseStation = baseStationRepository.findById(dataFromJson.getBaseStationId());

            List<MobileStationReport> mobileStationFromJson = dataFromJson.getReports();
            List<String> listOfNotFoundMobileIDs = new ArrayList<>();

            ApiResponseModel.ApiStatusAndMessage statusAndMessage = new ApiResponseModel.ApiStatusAndMessage();
            for (MobileStationReport mobileStationReport : mobileStationFromJson) {
                String mobileID = mobileStationReport.getMobileStationId();
                Optional<MobileStation> mobileStation = mobileStationRepository.findById(mobileID);
                Boolean alrealdyExistMobile = baseStationReportRepository.existsByBaseStationIdAndMobileStationId(dataFromJson.getBaseStationId(), mobileID);
                if (baseStation.isPresent() && mobileStation.isPresent() && !alrealdyExistMobile) {
                    Report reportToSave = new Report();
                    reportToSave.setMobileStation(mobileStation.get());
                    reportToSave.setTimestamp(mobileStationReport.getTimestamp());
                    reportToSave.setDistance(mobileStationReport.getDistance());
                    reportToSave.setBaseStation(baseStation.get());
                    baseStationReportRepository.save(reportToSave);
                } else {
                    listOfNotFoundMobileIDs.add(mobileStationReport.getMobileStationId());
                }
            }

            response.setSuccess(true);
            response.setData(null);
            statusAndMessage.setStatus(ApiResponseConsts.INSERTED_CODE);
            if (listOfNotFoundMobileIDs.size() > 0) {
                statusAndMessage.setMessage(ApiResponseConsts.INSERTED_MESSAGE + " with the follow mobileID Not Inserted " + listOfNotFoundMobileIDs);
            } else {
                statusAndMessage.setMessage(ApiResponseConsts.INSERTED_MESSAGE);
            }
            response.setResponseInformation(statusAndMessage);
            jsonResponse = gson.toJson(response);
            return jsonResponse;
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            response.setData(null);
            response.setResponseInformation(null);
            response.setSuccess(false);
            apiStatusAndMessage.setStatus(ApiResponseConsts.DUPLICATE_DATA_CODE);
            apiStatusAndMessage.setMessage(ApiResponseConsts.DUPLICATE_DATA_MESSAGE);
            jsonResponse = gson.toJson(response);
            return jsonResponse;
        }
    }
}
