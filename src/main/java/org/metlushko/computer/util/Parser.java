package org.metlushko.computer.util;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Parser {
    public static Map<String, String> parseJsonToMapStrings(String json) {
        return Arrays.stream((json.substring(1, json.length() - 1).split(",")))
                .map(s -> s.split(":"))
                .collect(Collectors.toMap(key -> key[0].trim().replace("\"",""),
                        value -> value[1].trim().replace("\"","")));
    }
}
