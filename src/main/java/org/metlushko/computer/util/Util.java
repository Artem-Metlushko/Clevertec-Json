package org.metlushko.computer.util;


import lombok.experimental.UtilityClass;

import java.util.Map;

@UtilityClass
public class Util {
    public static <T> T  convertJsonToInstance(String json, Class<T> clazz) {
        T newInstance = createNewInstance(clazz);
        Map<String, String> mapParser = Parser.stringToMapParser(json);
        T declaredInstance = Mapper.mapTo(clazz, mapParser, newInstance);

        return declaredInstance;
    }

    private static <T> T createNewInstance(Class<T> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



}
