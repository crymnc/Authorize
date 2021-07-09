package com.experiment.authorize.util;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

public class ReflectionUtils {

    public static Field findSubField(Class mainClass, Class subClass) {
        if(mainClass == null || subClass == null)
            return null;
        for (Field field : mainClass.getDeclaredFields()) {
            ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
            if (parameterizedType.getActualTypeArguments()[0].equals(subClass)) {
                return field;
            }
        }
        return null;
    }
}
