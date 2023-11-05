package org.metlushko.computer.util;

import lombok.experimental.UtilityClass;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
@UtilityClass
public class Mapper {
    public static <T> T mapTo(Class<T> clazz, Map<String, String> mapFields, T newInstance) {

        Map<String, Field> mapNameWithField = Arrays.stream(clazz.getDeclaredFields())
                .collect(Collectors.toMap(Field::getName, f -> f));
        mapNameWithField.values()
                .forEach(field -> field.setAccessible(true));
        mapNameWithField.values()
                .forEach(field -> setValueForType(newInstance, field, mapFields));

        return newInstance;
    }


    private static <T> void setValueForType(T newInstance, Field f, Map<String, String> mapFields) {
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
