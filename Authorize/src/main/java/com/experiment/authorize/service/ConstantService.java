package com.experiment.authorize.service;

import com.experiment.authorize.domain.base.BaseConstantModel;
import com.experiment.authorize.entity.base.BaseConstantEntity;
import com.experiment.authorize.exception.BusinessException;
import com.experiment.authorize.mapper.ConstantMapper;
import com.experiment.authorize.service.base.EntityService;
import com.experiment.authorize.util.ReflectionUtils;
import com.experiment.authorize.util.hierarchy.HierarchyManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ConstantService extends EntityService {

    @Autowired
    ConstantMapper constantMapper;

    @Autowired
    private CacheManager cacheManager;


    @Cacheable(value = "findReference", key = "#name + #c.getName()")
    public <T extends BaseConstantEntity> T find(String name, Class<T> c) {
        T instance = createInstance(c);
        instance.setName(name);
        return (T) entityRepository.findOne(Example.of(instance)).get();
    }

    @Cacheable(value ="findReferenceByName", key="#constantName")
    public List findAllByConstantName(String constantName) {
        return findAll(HierarchyManager.getHierarchyByName(constantName).getClazz())
                .stream()
                .map(constantEntity -> constantMapper.toModel(constantEntity))
                .collect(Collectors.toList());
    }

    public void deleteByConstantNameAndId(String constantName, Long id) {
        delete(id, HierarchyManager.getHierarchyByName(constantName).getClazz());
        cacheManager.getCache("findReferenceByName").evict(constantName);
    }

    public <T extends BaseConstantEntity> T saveByConstantName(String constantName, BaseConstantModel constantModel) {
        if (constantModel.getId() != null)
            throw new BusinessException("If id field is not null, try to update " + constantName);
        T t = save(constantMapper.toEntity(constantModel, HierarchyManager.getHierarchyByName(constantName).getClazz()));
        cacheManager.getCache("findReferenceByName").evict(constantName);
        return t;
    }

    public <T extends BaseConstantEntity> T updateByConstantName(String constantName, BaseConstantModel constantModel) {
        if (constantModel.getId() == null)
            throw new BusinessException("If id field is null, try to create " + constantName);
        T t = save(constantMapper.toEntity(constantModel, HierarchyManager.getHierarchyByName(constantName).getClazz()));
        cacheManager.getCache("findReferenceByName").evict(constantName);
        return t;

    }

    public void addSubConstantToMain(String mainConstantName, Long mainConstantId, String subConstantName, Long subConstantId) {
        Field subField = ReflectionUtils.findSubField(HierarchyManager.getHierarchyByName(mainConstantName).getClazz(), HierarchyManager.getHierarchyByName(subConstantName).getClazz());
        if (subField == null)
            throw new BusinessException(mainConstantName + " do not have " + subConstantName + " field");

        BaseConstantEntity toEntity = find(mainConstantId,HierarchyManager.getHierarchyByName(mainConstantName).getClazz()).orElseThrow(() -> new BusinessException(mainConstantName + " is not found with given id"));
        try {
            subField.setAccessible(true);
            Set set = (Set) subField.get(toEntity);
            set.add(find(subConstantId, HierarchyManager.getHierarchyByName(subConstantName).getClazz()).orElseThrow(() -> new BusinessException(subConstantName + " is not found with given id")));
            save(toEntity);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void removeSubConstantFromMain(String mainConstantName, Long mainConstantId, String subConstantName, Long subConstantId) {
        Field subField = ReflectionUtils.findSubField(HierarchyManager.getHierarchyByName(mainConstantName).getClazz(), HierarchyManager.getHierarchyByName(subConstantName).getClazz());
        if (subField == null)
            throw new BusinessException(mainConstantName + " do not have " + subConstantName + " field");
        BaseConstantEntity mainEntity = find(mainConstantId, HierarchyManager.getHierarchyByName(mainConstantName).getClazz()).orElseThrow(() -> new BusinessException(mainConstantName + " is not found with given id"));
        try {
            subField.setAccessible(true);
            Set set = (Set) subField.get(mainEntity);
            set.remove(find(subConstantId, HierarchyManager.getHierarchyByName(subConstantName).getClazz()).orElseThrow(() -> new BusinessException(subConstantName + " is not found with given id")));
            save(mainEntity);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Cacheable(value ="getAllSubConstantName", key="#mainConstantName+#mainConstantId+#subConstantName")
    public List getSubConstants(String mainConstantName, Long mainConstantId, String subConstantName){
        Field subField = ReflectionUtils.findSubField(HierarchyManager.getHierarchyByName(mainConstantName).getClazz(), HierarchyManager.getHierarchyByName(subConstantName).getClazz());
        if (subField == null)
            throw new BusinessException(mainConstantName + " do not have " + subConstantName + " field");
        BaseConstantEntity mainEntity = find(mainConstantId,HierarchyManager.getHierarchyByName(mainConstantName).getClazz()).orElseThrow(() -> new BusinessException(mainConstantName+" is not found with given id"));
        try {
            subField.setAccessible(true);
            Set subFieldValue = (Set)subField.get(mainEntity);
            if(!CollectionUtils.isEmpty(subFieldValue)){
                return (List) subFieldValue.stream().map(o -> constantMapper.toModel((BaseConstantEntity)o)).collect(Collectors.toList());
            }
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List getAllSubConstantName(String mainConstantName) {
        return HierarchyManager.getHierarchyByName(mainConstantName).getSubConstants().stream()
                .map(constantHierarchy -> new BaseConstantModel(constantHierarchy.getName(),constantHierarchy.getDesc()))
                .collect(Collectors.toList());
    }
}
