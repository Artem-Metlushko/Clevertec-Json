package org.metlushko.computer.util;


import org.metlushko.computer.entyti.enums.FieldType;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Deserialization {
    public static <T> T readValue(String json, Class<T> clazz) {

        T objOrder = createNewInstance(clazz);
        Map<String, Object> map =  (Map<String, Object>) Parser.parseJSON(json);
        deserializeObject(objOrder, map, clazz);

        return objOrder;

    }

    private static <T> T createNewInstance(Class<T> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static <T> void deserializeObject(T object, Map<String, Object> map, Class<T> clazz) {

        map.forEach((fieldName, fieldValue) -> {
            try {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                Class<?> fieldType = field.getType();

                switch (getFieldType(fieldType)) {
                    case UUID -> field.set(object, UUID.fromString((String) fieldValue));
                    case STRING_OR_DOUBLE -> field.set(object, fieldValue);
                    case LOCAL_DATE -> field.set(object, LocalDate.parse((String )fieldValue));
                    case LIST -> {
                        List<Object> list = getList((List<Map<String, Object>>) fieldValue, field);
                        field.set(object, list);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
    private static <T> List<Object> getList(List<Map<String, Object>> fieldValue, Field field)  {
        List<Object> list = new ArrayList<>();
        for (Map<String, Object> itemMap : fieldValue) {
            Class<?> itemClass = determineItemType(field);
            Object item = createNewInstance(itemClass);
            deserializeObject((T) item, itemMap, (Class<T>) itemClass);
            list.add(item);
        }
        return list;
    }

    private static Class<?> determineItemType(Field field) {
        return (Class<?>) ((java.lang.reflect.ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
    }


    private static FieldType getFieldType(Class<?> fieldType) {
        if (fieldType.equals(UUID.class)) {
            return FieldType.UUID;
        } else if (fieldType.equals(String.class) || fieldType.equals(Double.class)) {
            return FieldType.STRING_OR_DOUBLE;
        } else if (fieldType.equals(LocalDate.class)) {
            return FieldType.LOCAL_DATE;
        } else if (fieldType.equals(List.class)) {
            return FieldType.LIST;
        }
        throw new IllegalArgumentException("Unsupported field type: " + fieldType);
    }


}
