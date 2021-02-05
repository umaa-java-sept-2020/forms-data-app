package com.java.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

// ObjectMapper by jackson
public class JsonUtils {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static <T> T toObject(InputStream is, Class<T> classType) throws IOException {
        T o = MAPPER.readValue(is, classType);
        return o;
    }

    public static String toJson(Object object) throws JsonProcessingException {
        String json = MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        return json;
    }
}
