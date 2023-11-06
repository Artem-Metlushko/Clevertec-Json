package org.metlushko.computer.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import org.metlushko.computer.entyti.Computer;
import org.metlushko.computer.entyti.Engineer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
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
        String jsonComputer = "{\"id\":\"bb22f153-5ca3-4af9-847d-527549641e02\",\"brand\":\"Samsung\",\"model\":\"xxx\",\"price\":100.0,\"dateTime\":\"1964-02-03\"}";

        String jsonCustomer = "{\n" +
                "  \"UUID\": \"bb22f153-5ca3-4af9-847d-527549641e02\",\n" +
                "  \"name\": \"BobDilan\",\n" +
                "  \"dateBirthday\": \"1961-11-14T10:45\",\n" +
                "  \"computers\": [\n" +
                "    {\n" +
                "      \"id\": \"bb22f153-5ca3-4af9-847d-527549641e02\",\n" +
                "      \"brand\": \"Nokia\",\n" +
                "      \"model\": \"X100\",\n" +
                "      \"price\": \"999.99\",\n" +
                "      \"dateTime\": \"1961-11-14T10:45\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"bb22f153-5ca3-4af9-847d-527549641e02\",\n" +
                "      \"brand\": \"Nokia\",\n" +
                "      \"model\": \"X100\",\n" +
                "      \"price\": \"999.99\",\n" +
                "      \"dateTime\": \"1961-11-14T10:45\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        Computer computer1 = new Computer(UUID.fromString("bb22f153-5ca3-4af9-847d-527549641e02"),
                "Samsung", "xxx", 100.0, LocalDate.of(1964, Month.FEBRUARY, 3)); ;

        List<Computer> arrayList = new ArrayList<>();
        arrayList.add(computer1);
        arrayList.add(computer1);

        new Engineer(UUID.fromString("bb22f153-5ca3-4af9-847d-527549641e02"),
                "BobDilan",LocalDateTime.of(1961, 11, 14, 10, 45), arrayList);


        /*System.out.println("===>toInstance(jsonComputer, Computer.class) =  "+toInstance(jsonComputer, Computer.class));
        System.out.println();

        System.out.println("===>toJson(Computer.class,computer1) =  "+toJson(Computer.class,computer1));
        System.out.println();*/

        /*System.out.println("===>toInstance(jsonCustomer, Customer.class)) =  "+toInstance(jsonCustomer, Engineer.class));
        System.out.println();*/

//        System.out.println(jackionToJson(computer1));
        System.out.println(jackionToObject(jsonComputer, Computer.class));


    }

    public static String jackionToJson(Object obj)  {

        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> Object jackionToObject(String json,Class <T> tClass){
        try {
            return new ObjectMapper().readValue(json,Computer.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
