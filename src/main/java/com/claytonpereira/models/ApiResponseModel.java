package com.claytonpereira.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponseModel<T> {
    private boolean success;
    private T data;
    private ApiStatusAndMessage responseInformation = new ApiStatusAndMessage();

    @Getter
    @Setter
    public static class ApiStatusAndMessage {
        private int status;
        private String message;
    }

}


