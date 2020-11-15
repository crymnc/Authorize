package com.anatoliapark.nursinghome.service;

import com.anatoliapark.nursinghome.entity.base.BaseConstantEntity;
import com.anatoliapark.nursinghome.repository.ConstantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConstantService<T extends BaseConstantEntity> extends BaseService {

    @Autowired
    ConstantRepository<T> constantRepository;

    public T save(BaseConstantEntity constantEntity) {
        if(constantEntity != null)
            return constantRepository.save((T)constantEntity);
        return null;
    }

    public T findByName(String name, Class<T> c) {
        T instance = createInstance(c);
        instance.setName(name);
        return constantRepository.findOne(Example.of(instance));
    }

    public BaseConstantEntity find(Long id, Class<T> c) {
        ExampleMatcher matcher = ExampleMatcher.matchingAll();
        T instance = createInstance(c);
        instance.setId(id);
        if(id != null)
            return constantRepository.findOne(Example.of(instance, matcher));
        return null;
    }

    public List<T> findAll(Class<T> c) {
        T instance = createInstance(c);
        return constantRepository.findAll(Example.of(instance));
    }
}
