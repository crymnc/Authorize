package com.experiment.accounting.service.base;

import java.lang.reflect.InvocationTargetException;

public abstract class BaseService {
    protected  <T> T createInstance(Class<T> c){
        try {
            return c.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
