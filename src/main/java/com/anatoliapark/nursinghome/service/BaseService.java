package com.anatoliapark.nursinghome.service;

public abstract class BaseService {
    protected  <T> T createInstance(Class<T> c){
        try {
            return c.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
