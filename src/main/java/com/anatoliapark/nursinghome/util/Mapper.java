package com.anatoliapark.nursinghome.util;

import com.anatoliapark.nursinghome.entity.base.BaseEntity;
import com.anatoliapark.nursinghome.model.base.BaseModel;
import lombok.extern.log4j.Log4j2;

import java.lang.reflect.Constructor;
import java.util.*;

@Log4j2
public class Mapper {

    public static <T extends BaseModel> List<T> getModelList(Collection<? extends BaseEntity> entityCollection){
        List<T> modelList = new ArrayList<>();
        for(BaseEntity entity : entityCollection){
            T modal = entity.getModal();
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
        for(BaseModel baseModel : modelCollection){
            try {
                Class<T> entityClass = baseModel.getEntityClass();
                Constructor<T> constructor = entityClass.getConstructor(BaseModel.class);
                entityList.add(constructor.newInstance(baseModel));
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
}
