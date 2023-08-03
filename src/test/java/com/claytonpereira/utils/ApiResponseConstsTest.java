package com.claytonpereira.utils;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiResponseConstsTest {
      @Test
    public void testApiResponseConstants() {
        assertEquals(404, ApiResponseConsts.NOT_FOUND_CODE);
        assertEquals("Nothing found.", ApiResponseConsts.NOT_FOUND_MESSAGE);

        assertEquals(500, ApiResponseConsts.INTERNAL_SERVER_ERROR_CODE);
        assertEquals("Internal Server Error", ApiResponseConsts.INTERNAL_SERVER_ERROR_MESSAGE);

        assertEquals(200, ApiResponseConsts.OK_CODE);
        assertEquals("Data retrieved successfully", ApiResponseConsts.OK_MESSAGE);

        assertEquals(201, ApiResponseConsts.INSERTED_CODE);
        assertEquals("Data inserted successfully", ApiResponseConsts.INSERTED_MESSAGE);

        assertEquals(409, ApiResponseConsts.DUPLICATE_DATA_CODE);
        assertEquals("Duplicate data conflict", ApiResponseConsts.DUPLICATE_DATA_MESSAGE);
    }
}