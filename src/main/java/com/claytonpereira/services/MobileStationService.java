package com.claytonpereira.services;

import com.claytonpereira.models.ApiResponseModel;
import com.claytonpereira.models.MobileStation;
import com.claytonpereira.repositories.BaseStationRepository;
import com.claytonpereira.repositories.MobileStationRepository;
import com.claytonpereira.utils.ApiResponseConsts;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MobileStationService {
    private final MobileStationRepository mobileStationRepository;

    Gson gson = new Gson();
    String jsonResponse;
    @Autowired
    public MobileStationService(MobileStationRepository mobileStationRepository) {
        this.mobileStationRepository = mobileStationRepository;


    }
    public String getMobileStationByUUID(String uuid) {
        ApiResponseModel<MobileStation> response = new ApiResponseModel<>();
        ApiResponseModel.ApiStatusAndMessage error = new ApiResponseModel.ApiStatusAndMessage();
        Optional<MobileStation> mobileStationOptional = mobileStationRepository.findById(uuid);
        try {
            if (mobileStationOptional.isPresent()) {
                MobileStation mobileStation = mobileStationOptional.get();
                response.setSuccess(true);
                response.setData(mobileStation);
                error.setStatus(ApiResponseConsts.OK_CODE);
                error.setMessage(ApiResponseConsts.OK_MESSAGE);
                response.setResponseInformation(error);
                jsonResponse = gson.toJson(response);
                return jsonResponse;
            } else {
                response.setSuccess(false);
                response.setData(null);
                error.setStatus(ApiResponseConsts.NOT_FOUND_CODE);
                error.setMessage(ApiResponseConsts.NOT_FOUND_MESSAGE);
                response.setResponseInformation(error);
                jsonResponse = gson.toJson(response);

                return jsonResponse;
            }
        }
           catch (Exception e) {
               response.setSuccess(false);
               response.setData(null);
               error.setStatus(ApiResponseConsts.INTERNAL_SERVER_ERROR_CODE);
               error.setMessage(ApiResponseConsts.INTERNAL_SERVER_ERROR_MESSAGE);
               response.setResponseInformation(error);
               jsonResponse = gson.toJson(response);
             return jsonResponse;
       }
    }
}