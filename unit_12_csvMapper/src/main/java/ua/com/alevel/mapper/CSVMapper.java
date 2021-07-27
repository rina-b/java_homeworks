package ua.com.alevel.mapper;

import ua.com.alevel.annotation.CSVMapp;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CSVMapper {
    public  <T> T map(Class<T> tClass, Map<String, Integer> header, String[] body) {
        T inst;
        List <T> list = new ArrayList<>();
        try {
            inst = tClass.getConstructor().newInstance();
            Field[] fields = tClass.getFields();
            for (Field field : fields) {

                if (field.isAnnotationPresent(CSVMapp.class)) {
                    CSVMapp mapp = field.getAnnotation(CSVMapp.class);
                    Object v = body[header.get(mapp.value())];
                    String value = String.valueOf(v);
                    Class<?> type = field.getType();
                    if (type == byte.class || type == Byte.class) {
                        field.set(inst, Byte.parseByte(value));
                    } else if (type == short.class || type == Short.class) {
                        field.set(inst, Short.parseShort(value));
                    } else if (type == int.class || type == Integer.class) {
                        field.set(inst, Integer.parseInt(value));
                    } else if (type == long.class || type == Long.class) {
                        field.set(inst, Long.parseLong(value));
                    } else if (type == float.class || type == Float.class) {
                        field.set(inst, Float.parseFloat(value));
                    } else if (type == double.class || type == Double.class) {
                        field.set(inst, Double.parseDouble(value));
                    } else if (type == boolean.class || type == Boolean.class) {
                        field.set(inst, Boolean.parseBoolean(value));
                    } else if (type == char.class || type == Character.class) {
                        field.set(inst, value.charAt(0));
                    } else if (type == String.class) {
                        field.set(inst, value);
                    } else if (type.isEnum()) {
                        field.set(inst, Enum.valueOf((Class<? extends Enum>) type, value));
                    }
                }
            }
        }
        catch (InstantiationException | IllegalAccessException |
                InvocationTargetException | NoSuchMethodException e){
            throw new RuntimeException(e);
        }
        return inst;
    }
}
