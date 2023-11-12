package org.metlushko.computer.util;

import org.metlushko.computer.entyti.enums.JsonType;

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;

public class Parser {

    public static Object parseJSON(String json) {
        json = json.trim();

        switch (getJsonType(json)) {
            case OBJECT:
                Map<String, Object> map = new LinkedHashMap<>();
                json = json.substring(1, json.length() - 1);

                List<String> pairs = splitJSONPairs(json);

                for (String pair : pairs) {
                    int colonIndex = findColonIndex(pair);
                    String key = pair.substring(0, colonIndex).trim().replace("\"", "");
                    String value = pair.substring(colonIndex + 1).trim();
                    Object parsedValue = parseJSON(value);
                    map.put(key, parsedValue);
                }

                return map;

            case ARRAY:
                List<Object> list = new ArrayList<>();
                json = json.substring(1, json.length() - 1);

                List<String> elements = splitJSONArrayElements(json);

                for (String element : elements) {
                    list.add(parseJSON(element));
                }

                return list;

            case BOOLEAN:
                return Boolean.parseBoolean(json);

            case NULL:
                return null;

            case STRING:
                return json.replace("\"", "");

            case NUMBER:
                return json.contains(".") ? Double.parseDouble(json) : Long.parseLong(json);

            default:
                throw new IllegalArgumentException("Unsupported data type: " + json);
        }
    }

    private static JsonType getJsonType(String json) {
        if (json.startsWith("{") && json.endsWith("}")) {
            return JsonType.OBJECT;
        } else if (json.startsWith("[") && json.endsWith("]")) {
            return JsonType.ARRAY;
        } else if (json.equals("true") || json.equals("false")) {
            return JsonType.BOOLEAN;
        } else if (json.equals("null")) {
            return JsonType.NULL;
        } else if (json.matches("\".*\"")) {
            return JsonType.STRING;
        } else if (json.matches("-?\\d+(\\.\\d+)?")) {
            return JsonType.NUMBER;
        } else {
            return JsonType.UNKNOWN;
        }
    }

    private static List<String> splitJSONPairs(String json) {
        List<String> pairs = new ArrayList<>();
        int openBraces = 0;
        int openBrackets = 0;
        int start = 0;

        for (int i = 0; i < json.length(); i++) {
            char c = json.charAt(i);

            if (c == '{' || c == '[') {
                openBraces++;
                if (c == '[') {
                    openBrackets++;
                }
            } else if (c == '}' || c == ']') {
                openBraces--;
                if (c == ']') {
                    openBrackets--;
                }
            } else if (c == ',' && openBraces == 0 && openBrackets == 0) {
                pairs.add(json.substring(start, i));
                start = i + 1;
            }
        }

        pairs.add(json.substring(start));
        return pairs;
    }

    private static int findColonIndex(String json) {
        int openBrackets = 0;

        for (int i = 0; i < json.length(); i++) {
            char c = json.charAt(i);

            if (c == '{' || c == '[') {
                openBrackets++;
            } else if (c == '}' || c == ']') {
                openBrackets--;
            } else if (c == ':' && openBrackets == 0) {
                return i;
            }
        }
        throw new IllegalArgumentException("Invalid JSON format: " + json);
    }

    private static List<String> splitJSONArrayElements(String json) {
        List<String> elements = new ArrayList<>();
        int openBraces = 0;
        int openBrackets = 0;
        int start = 0;

        for (int i = 0; i < json.length(); i++) {
            char c = json.charAt(i);

            if (c == '{' || c == '[') {
                openBraces++;
                if (c == '[') {
                    openBrackets++;
                }
            } else if (c == '}' || c == ']') {
                openBraces--;
                if (c == ']') {
                    openBrackets--;
                }
            } else if (c == ',' && openBraces == 0 && openBrackets == 0) {
                elements.add(json.substring(start, i));
                start = i + 1;
            }
        }

        elements.add(json.substring(start));
        return elements;
    }
}
