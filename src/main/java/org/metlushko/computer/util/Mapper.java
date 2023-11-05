package org.metlushko.computer.util;

import lombok.experimental.UtilityClass;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@UtilityClass
public class Mapper {
    public static <T> T toInstance(Class<T> clazz, Map<String, String> mapFields, T newInstance) {

        Map<String, Field> mapNameWithField = Arrays.stream(clazz.getDeclaredFields())
                .collect(Collectors.toMap(Field::getName, f -> f));
        mapNameWithField.values()
                .forEach(field -> field.setAccessible(true));
        mapNameWithField.values()
                .forEach(field -> setValueIntoInstance(newInstance, field, mapFields));

        return newInstance;
    }


    public static <T> String toJson(Class<T> clazz, T obj, StringBuilder json) {
        Arrays.stream(clazz.getDeclaredFields())
                .peek(field -> field.setAccessible(true))
                .forEach(field -> appendToJson(field, json, obj));

        return json.toString();

    }

/*    private static <T> Map<String, Field> getMapNameWithField(Class<T> clazz) {

        Map<String, Field> mapNameWithField = Arrays.stream(clazz.getDeclaredFields())
                .collect(Collectors.toMap(Field::getName, f -> f));
        mapNameWithField.values()
                .forEach(field -> field.setAccessible(true));

        return mapNameWithField;
    }*/

    private static void appendToJson(Field field, StringBuilder json, Object obj) {

        try {
            Object value = field.get(obj);
            String nameType = field.getType().getSimpleName();

            json.append("\"").append(nameType).append("\":");
            switch (nameType) {
                case "String", "UUID", "Double" -> json.append("\"").append(value).append("\",");
                default -> json.append("empty");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> String toJson2(Class<T> clazz, T obj) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        Field[] fields = obj.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);

            sb.append("\"");
            sb.append(field.getName());
            sb.append("\":");

            Object value = null;
            try {
                value = field.get(obj);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            if (value == null) {
                sb.append("null");
            } else if (value instanceof String) {
                sb.append("\"");
                sb.append(value);
                sb.append("\"");
            } else {
                sb.append(value);
            }

            if (i < fields.length - 1) {
                sb.append(",");
            }
        }

        sb.append("}");
        return sb.toString();
    }


    private static <T> void setValueIntoInstance(T newInstance, Field f, Map<String, String> mapFields) {
        String currentValue = mapFields.get(f.getName());
        String nameOfTypeField = f.getType().getSimpleName();
        try {
            switch (nameOfTypeField) {
                case "String" -> f.set(newInstance, currentValue);
                case "UUID" -> f.set(newInstance, UUID.fromString(currentValue));
                case "Double" -> f.set(newInstance, Double.valueOf(currentValue));
                default -> f.set(newInstance, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
