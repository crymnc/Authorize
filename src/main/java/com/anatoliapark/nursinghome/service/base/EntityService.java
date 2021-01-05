package com.anatoliapark.nursinghome.service.base;

import com.anatoliapark.nursinghome.entity.base.BaseConstantEntity;
import com.anatoliapark.nursinghome.entity.base.BaseEntity;
import com.anatoliapark.nursinghome.repository.base.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntityService<T extends BaseEntity> extends BaseService{

    @Autowired
    protected EntityRepository<T> entityRepository;

    public T save(BaseConstantEntity constantEntity) {
        if(constantEntity != null)
            return entityRepository.save((T)constantEntity);
        return null;
    }

    public T find(Long id, Class<T> c) {
        ExampleMatcher matcher = ExampleMatcher.matchingAll();
        T instance = createInstance(c);
        instance.setId(id);
        if(id != null)
            return entityRepository.findOne(Example.of(instance, matcher)).get();
        return null;
    }

    public List<T> findAll(Class<T> c) {
        T instance = createInstance(c);
        return entityRepository.findAll(Example.of(instance));
    }

    public void delete(Long id, Class<T> c){
        T instance = createInstance(c);
        instance.setId(id);
        entityRepository.delete(instance);
    }
}
