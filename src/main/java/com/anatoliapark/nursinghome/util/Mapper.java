package com.anatoliapark.nursinghome.util;

import com.anatoliapark.nursinghome.annotation.EntityMapping;
import com.anatoliapark.nursinghome.annotation.ModelMapping;
import com.anatoliapark.nursinghome.entity.base.BaseEntity;
import com.anatoliapark.nursinghome.model.base.BaseModel;
import lombok.extern.log4j.Log4j2;
import org.hibernate.LazyInitializationException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Log4j2
public class Mapper {

    public static <T extends BaseModel> List<T> getModelList(Collection<? extends BaseEntity> entityCollection){
        List<T> modelList = new ArrayList<>();
        if(entityCollection != null)
            for(BaseEntity entity : entityCollection){
                T modal = convertToModel(entity);
                if(modal != null)
                    modelList.add(modal);
            }
        return modelList;
    }

    public static <T extends BaseModel> Set<T> getModelSet(Collection<? extends BaseEntity> entityCollection){
        return new HashSet<>(getModelList(entityCollection));
    }

    public static <T extends BaseEntity> List<T> getEntityList(Collection<? extends BaseModel> modelCollection){
        List<T> entityList = new ArrayList<>();
        if(modelCollection != null)
            for(BaseModel baseModel : modelCollection){
                try {
                    T entity = convertToEntity(baseModel);
                    if(entity != null)
                        entityList.add(entity);
                }
                catch (Exception e){
                    log.error(e);
                }
            }
        return entityList;
    }

    public static <T extends BaseEntity> Set<T> getEntitySet(Collection<? extends BaseModel> modelCollection){
        return new HashSet<>(getEntityList(modelCollection));
    }

    public static <T extends BaseModel> T convertToModel(BaseEntity entity) {
        try {
            Class<T> modelClass = getModelClass(entity);
            for(Constructor constructor: modelClass.getConstructors()){
                if(constructor.getParameterCount() == 1 && constructor.getParameterTypes()[0].equals(entity.getClass())){
                    return (T) constructor.newInstance(entity);
                }
            }
            throw new RuntimeException("Model constructor with parameter is missing");
        }
        catch (InvocationTargetException e){
            if(e.getTargetException() instanceof LazyInitializationException){
                return null;
            }
            log.error(e);
        }
        catch (Exception e) {
            log.error(e.getStackTrace());
        }
        return null;
    }

    protected static <T extends BaseModel> Class<T> getModelClass(BaseEntity entity){
        Annotation annotation = entity.getClass().getAnnotation(ModelMapping.class);
        if(annotation != null){
            try {
                Class<T> modelClass = ((ModelMapping) annotation).modelClass();
                return modelClass;
            }
            catch (Exception e){
                log.error(e);
            }
        }
        else{
            throw new RuntimeException("ModelMapping annotation not found on entity");
        }
        return null;
    }

    public static <T extends BaseEntity> T convertToEntity(BaseModel model) {
        try {
            Class<T> entityClass = getEntityClass(model);
            for(Constructor constructor: entityClass.getConstructors()){
                if(constructor.getParameterCount() == 1 && constructor.getParameterTypes()[0].equals(model.getClass())){
                    return (T) constructor.newInstance(model);
                }
            }
            throw new RuntimeException("Entity constructor with parameter is missing");
        }
        catch (Exception e) {
            log.error(e.getStackTrace());
        }
        return null;
    }

    protected static <T extends BaseEntity> Class<T> getEntityClass(BaseModel model){
        Annotation annotation = model.getClass().getAnnotation(EntityMapping.class);
        if(annotation != null){
            try {
                Class<T> entityClass = ((EntityMapping) annotation).entityClass();
                return entityClass;
            }
            catch (Exception e){
                log.error(e);
            }
        }
        else{
            throw new RuntimeException("EntityMapping annotation not found on model");
        }
        return null;
    }

}
