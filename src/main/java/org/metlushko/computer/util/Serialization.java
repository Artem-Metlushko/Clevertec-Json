package org.metlushko.computer.util;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class Serialization {

    public static String writeValueAsString(Object object) {
        StringBuilder json = new StringBuilder("{");

        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);

            Object value = getObject(object, field);
            if (value != null) {
                if (field.getType().isAssignableFrom(List.class)) {
                    json.append("\"").append(field.getName()).append("\": [");

                    List<?> list = (List<?>) value;
                    for (Object item : list) {
                        json.append(writeValueAsString(item)).append(",");
                    }

                    if (!list.isEmpty()) {
                        json.deleteCharAt(json.length() - 1);
                    }

                    json.append("],");
                } else {
                    appendJson(json, field, value);
                }
            }
        }

        if (json.charAt(json.length() - 1) == ',') {
            json.deleteCharAt(json.length() - 1);
        }

        json.append("}");

        return json.toString().replace(" ", "");
    }

    private static void appendJson(StringBuilder json, Field field, Object value) {
        json.append("\"").append(field.getName()).append("\":");
        if (field.getType().isAssignableFrom(String.class) || field.getType().isAssignableFrom(UUID.class)) {
            json.append("\"").append(value).append("\",");
        } else if (field.getType().isAssignableFrom(LocalDate.class)) {
            json.append("\"").append(value).append("\",");
        } else if (field.getType().isAssignableFrom(Double.class)) {
            json.append(value).append(",");
        }
    }

    private static Object getObject(Object object, Field field) {
        try {
            return  field.get(object);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
