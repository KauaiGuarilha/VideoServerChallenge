package com.videoserverchallenge.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.videoserverchallenge.model.domain.EMessage;

public class JsonMapperUtils {

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(EMessage.NOT_WRITE_VALUE_JSON.getMessage());
        }
    }
}
