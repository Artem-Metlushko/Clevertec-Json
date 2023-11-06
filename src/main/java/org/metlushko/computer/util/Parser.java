package org.metlushko.computer.util;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Parser {
    public static Map<String, String> parseJsonToMapStrings(String json) {
        return Arrays.stream((json.substring(1, json.length() - 1).split(",")))
                .map(s -> s.split(":"))
                .collect(Collectors.toMap(key -> key[0].trim().replace("\"", ""),
                        value -> value[1].trim().replace("\"", "")));
    }

    public static Map<String, String> parseJsonToMap(String json) {
        Map<String, String> map = new HashMap<>();
        String regex="\"(.*?)\"";
        Matcher matcher = Pattern.compile(regex).matcher(json);
        List<String> keys = new ArrayList<>();
        while (matcher.find()) {
            keys.add(matcher.group(1));
        }


        String[] values = json.replaceAll("\\s", "").split("\"[^\"]*\":");
        for (int i = 1; i < values.length; i++) {
            String value = values[i];
            if (value.startsWith("{")) {
                map.put(keys.get(i - 1), parseJsonToMap(value).get(keys.get(i - 1)));
            } else if (value.startsWith("[")) {
                List<Object> list = new ArrayList<>();
                Matcher m = Pattern.compile("\\{.*?}").matcher(value);
                while (m.find()) {
                    list.add(parseJsonToMap(m.group()));
                }
                map.put(keys.get(i - 1), list.stream().toString());
            } else {
                map.put(keys.get(i - 1), value.replaceAll("[\",]", ""));
            }
        }
        map.entrySet().stream().forEach(System.out::println);
        return map;
    }

    public static void main(String[] args) {
        String json = "{\n" +
                "\"id\": \"bb22f153-5ca3-4af9-847d-527549641e02\",\n" +
                "\"brand\": \"Nokia\",\n" +
                "\"model\": \"X100\",\n" +
                "\"price\": \"999.99\",\n" +
                "\"dateTime\": \"1961-11-14T10:45\"\n" +
                "}";;
        Pattern pattern = Pattern.compile("\"(.*?)\"");
        Matcher matcher = pattern.matcher(json);
        while (matcher.find()){
            System.out.println(matcher.group());
        }



    }

    //public static Map<String, Object> parseJsonToMap(String json) {
    //    Map<String, Object> map = new HashMap<>();
    //    Matcher matcher = Pattern.compile("\"(.*?)\"").matcher(json);
    //    List<String> keys = new ArrayList<>();
    //    while (matcher.find()) {
    //        keys.add(matcher.group(1));
    //    }
    //    String[] values = json.replaceAll("\\s", "").split("\"[^\"]*\":");
    //    for (int i = 1; i < values.length; i++) {
    //        String value = values[i];
    //        if (value.startsWith("{")) {
    //            map.put(keys.get(i - 1), parseJsonToMap(value));
    //        } else if (value.startsWith("[")) {
    //            List<Object> list = new ArrayList<>();
    //            Matcher m = Pattern.compile("\\{.*?}").matcher(value);
    //            while (m.find()) {
    //                list.add(parseJsonToMap(m.group()));
    //            }
    //            map.put(keys.get(i - 1), list);
    //        } else {
    //            map.put(keys.get(i - 1), value.replaceAll("[\",]", ""));
    //        }
    //    }
    //    return map;
    //}

}
