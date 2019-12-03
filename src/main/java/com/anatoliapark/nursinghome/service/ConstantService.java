package com.anatoliapark.nursinghome.service;

import com.anatoliapark.nursinghome.model.base.BaseConstantEntity;
import com.anatoliapark.nursinghome.repository.ConstantRepository;
import com.anatoliapark.nursinghome.repository.impl.ConstantRepositoryImpl.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ConstantService {

    @Autowired
    ConstantRepository constantRepository;

    public BaseConstantEntity save(BaseConstantEntity constantEntity) {
        if(constantEntity != null)
            return constantRepository.save(constantEntity);
        return null;
    }

    public BaseConstantEntity findByName(String name, Class<BaseConstantEntity> c) {
        if(!StringUtils.isEmpty(name))
            return constantRepository.findByName(name,c);
        return null;
    }

    public BaseConstantEntity find(Long id, Class<BaseConstantEntity> c) {
        if(id != null)
            return constantRepository.find(id,c);
        return null;
    }

    public void delete(BaseConstantEntity constantEntity) {
        constantRepository.delete(constantEntity);
    }

    public List<BaseConstantEntity> findAll(Class<BaseConstantEntity> c) {
        return constantRepository.findAll(c);
    }

    public List<BaseConstantEntity> findAll(Class<BaseConstantEntity> c, SortOrder sortOrder) {
        return constantRepository.findAll(c,sortOrder);
    }
}
