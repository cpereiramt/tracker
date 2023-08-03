package com.claytonpereira.utils;

import org.springframework.stereotype.Component;

@Component
public class ApiResponseConsts {

    public static final int NOT_FOUND_CODE = 404;
    public static final String NOT_FOUND_MESSAGE = "Nothing found.";

    public static final int INTERNAL_SERVER_ERROR_CODE = 500;
    public static final String INTERNAL_SERVER_ERROR_MESSAGE = "Internal Server Error" ;
    public static final int OK_CODE = 200;
    public static final String OK_MESSAGE = "Data retrieved successfully" ;

    public static final int INSERTED_CODE = 201;
    public static final String INSERTED_MESSAGE = "Data inserted successfully" ;

    public static final int DUPLICATE_DATA_CODE = 409;
    public static final String DUPLICATE_DATA_MESSAGE = "Duplicate data conflict" ;
}
