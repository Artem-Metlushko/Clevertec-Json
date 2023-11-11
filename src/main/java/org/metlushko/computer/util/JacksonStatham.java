package org.metlushko.computer.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;

import java.io.IOException;

@UtilityClass
public class JacksonStatham {

    public static String toJson(Object obj)  {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> Object toObject(String json, Class <T> tClass){

        try {
            return  new ObjectMapper().readValue(json, tClass);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
