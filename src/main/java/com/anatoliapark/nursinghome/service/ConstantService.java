package com.anatoliapark.nursinghome.service;

import com.anatoliapark.nursinghome.domain.base.BaseConstantModel;
import com.anatoliapark.nursinghome.entity.UserComponentEntity;
import com.anatoliapark.nursinghome.entity.auth.AuthorityEntity;
import com.anatoliapark.nursinghome.entity.auth.AuthorityGroupEntity;
import com.anatoliapark.nursinghome.entity.auth.RoleEntity;
import com.anatoliapark.nursinghome.entity.base.BaseConstantEntity;
import com.anatoliapark.nursinghome.entity.webpage.WebPageComponentEntity;
import com.anatoliapark.nursinghome.entity.webpage.WebPageComponentTypeEntity;
import com.anatoliapark.nursinghome.entity.webpage.WebPageEntity;
import com.anatoliapark.nursinghome.exception.BussinessException;
import com.anatoliapark.nursinghome.mapper.ConstantMapper;
import com.anatoliapark.nursinghome.service.base.EntityService;
import com.anatoliapark.nursinghome.util.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ConstantService extends EntityService {

    @Autowired
    ConstantMapper constantMapper;

    private static final HashMap<String, Class<? extends BaseConstantEntity>> constantMap = new HashMap<>();

    static {
        constantMap.put("role", RoleEntity.class);
        constantMap.put("usercomponent", UserComponentEntity.class);
        constantMap.put("authority", AuthorityEntity.class);
        constantMap.put("authoritygroup",AuthorityGroupEntity.class);
        constantMap.put("webpagecomponent",WebPageComponentEntity.class);
        constantMap.put("webpagecomponenttype",WebPageComponentTypeEntity.class);
        constantMap.put("webpage",WebPageEntity.class);
    }

    public <T extends BaseConstantEntity> T find(String name, Class<T> c) {
        T instance = createInstance(c);
        instance.setName(name);
        return (T) entityRepository.findOne(Example.of(instance)).get();
    }

    public List findAllByConstantName(String constantName) {
        if (constantMap.containsKey(constantName)) {
            return findAll(constantMap.get(constantName))
                    .stream()
                    .map(constantEntity -> constantMapper.toModel(constantEntity))
                    .collect(Collectors.toList());
        }
        throw new BussinessException("Constant name is not acceptable");
    }

    public void deleteByConstantNameAndId(String constantName, Long id) {
        if (constantMap.containsKey(constantName)) {
            delete(id, constantMap.get(constantName));
        }
    }

    public <T extends BaseConstantEntity> T saveByConstantName(String constantName, BaseConstantModel constantModel) {
        if(constantModel.getId() != null)
            throw new BussinessException("If id field is not null, try to update "+constantName);
        if(constantMap.containsKey(constantName)){
            return save(constantMapper.toEntity(constantModel,constantMap.get(constantName)));
        }else{
            throw new BussinessException("Constant name is not acceptable");
        }
    }

    public <T extends BaseConstantEntity> T updateByConstantName(String constantName, BaseConstantModel constantModel) {
        if(constantModel.getId() == null)
            throw new BussinessException("If id field is null, try to create "+constantName);
        if(constantMap.containsKey(constantName)){
            return save(constantMapper.toEntity(constantModel,constantMap.get(constantName)));
        }else{
            throw new BussinessException("Constant name is not acceptable");
        }
    }

    public void addSubConstantToMain(String mainConstantName, Long mainConstantId, String subConstantName, Long subConstantId){
        Field subField = ReflectionUtils.findSubField(constantMap.get(mainConstantName), constantMap.get(subConstantName));
        if (subField == null)
            throw new BussinessException(mainConstantName + " do not have " + subConstantName + " field");

        BaseConstantEntity toEntity = find(mainConstantId,constantMap.get(mainConstantName)).orElseThrow(() -> new BussinessException(mainConstantName+" is not found with given id"));
        try {
            subField.setAccessible(true);
            Set set = (Set)subField.get(toEntity);
            set.add(find(subConstantId,constantMap.get(subConstantName)).orElseThrow(() -> new BussinessException(subConstantName+" is not found with given id")));
            save(toEntity);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void removeSubConstantFromMain(String mainConstantName, Long mainConstantId, String subConstantName, Long subConstantId){
        Field subField = ReflectionUtils.findSubField(constantMap.get(mainConstantName), constantMap.get(subConstantName));
        if (subField == null)
            throw new BussinessException(mainConstantName + " do not have " + subConstantName + " field");
        BaseConstantEntity mainEntity = find(mainConstantId,constantMap.get(mainConstantName)).orElseThrow(() -> new BussinessException(mainConstantName+" is not found with given id"));
        try {
            subField.setAccessible(true);
            Set set = (Set)subField.get(mainEntity);
            set.remove(find(subConstantId,constantMap.get(subConstantName)).orElseThrow(() -> new BussinessException(subConstantName+" is not found with given id")));
            save(mainEntity);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }



}
