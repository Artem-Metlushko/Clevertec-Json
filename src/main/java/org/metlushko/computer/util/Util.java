package org.metlushko.computer.util;


import lombok.experimental.UtilityClass;
import org.metlushko.computer.entyti.Computer;

import java.util.Map;
import java.util.UUID;

@UtilityClass
public class Util {
    public static <T> T toInstance(String json, Class<T> clazz) {

        T newInstance = createNewInstance(clazz);
        Map<String, String> mapParser = Parser.parseJsonToMapStrings(json);

        return Mapper.toInstance(clazz, mapParser, newInstance);
    }

    public static <T> String toJson(Class<T> clazz, T obj ) {
        StringBuilder json = new StringBuilder();

        json.append("{");

        Mapper.toJson(clazz,obj,json);

        json.deleteCharAt(json.length() - 1).append("}");

        return json.toString();
    }


    private static <T> T createNewInstance(Class<T> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) throws IllegalAccessException {
        String jsonComputer = "{\n" +
                "  \"id\": \"bb22f153-5ca3-4af9-847d-527549641e02\",\n" +
                "  \"brand\": \"Samsung\",\n" +
                "  \"model\": \"xxx\",\n" +
                "  \"price\": \"100.0\"\n" +
                "}";

        Computer computer = toInstance(jsonComputer, Computer.class);
        System.out.println(computer);

        Computer computer1 = new Computer(UUID.fromString("bb22f153-5ca3-4af9-847d-527549641e02"),
                "Samsung", "xxx", 100.0);


        System.out.println(Util.toJson(Computer.class,computer1));

    }


}
