package com.rubico.flight.ydomain;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

public class ConvertY<T> {

    public <T> T convert(Map<String, String> map, T t) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory()).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        objectMapper.findAndRegisterModules();

        Field[] fields = t.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(VCFilePathYML.class)) {
                String s = field.getAnnotation(VCFilePathYML.class).value();
                if (s.isEmpty()) {
                    for (Method method : t.getClass().getDeclaredMethods()) {
                        if (method.getName().toLowerCase().contains(field.getName()) && !method.getName().toLowerCase().contains("get")) {
                            Object b = objectMapper.readValue(map.get(field.getName().toLowerCase() + ".yaml"), field.getType());
                            method.invoke(t, b);

                        }
                    }

                } else {
                    for (Method method : t.getClass().getDeclaredMethods()) {
                        if (method.getName().toLowerCase().contains(field.getName()) && !method.getName().toLowerCase().contains("get")) {
                            Object b = objectMapper.readValue(map.get(s + "/" + field.getName().toLowerCase() + ".yaml"), field.getType());
                            //method.invoke(t, b);

                        }
                    }
                }

            }
        }
        return t;
    }
}