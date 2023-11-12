package org.metlushko.computer;


import lombok.experimental.UtilityClass;
import org.metlushko.computer.util.Deserialization;
import org.metlushko.computer.util.Serialization;

@UtilityClass
public class CustomObjectMapper {

    public static <T> Object  toObject(String json, Class <T> clazz) {
        return Deserialization.readValue(json, clazz);
    }
    public static String toJson(Object obj){
        return Serialization.writeValueAsString(obj);
    }

}
