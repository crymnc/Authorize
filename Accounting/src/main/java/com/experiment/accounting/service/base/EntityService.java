package com.experiment.accounting.service.base;

import com.experiment.accounting.entity.base.BaseEntity;
import com.experiment.accounting.repository.base.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntityService extends BaseService {

    protected final EntityRepository entityRepository;

    public EntityService(EntityRepository entityRepository){
        this.entityRepository = entityRepository;
    }

    public <T extends BaseEntity> T save(BaseEntity baseEntity) {
        if(baseEntity != null)
            return (T)entityRepository.save(baseEntity);
        return null;
    }

    public <T extends BaseEntity> Optional<T> find(Long id, Class<T> c) {
        ExampleMatcher matcher = ExampleMatcher.matchingAll();
        T instance = createInstance(c);
        instance.setId(id);
        if(id != null)
            return entityRepository.findOne(Example.of(instance, matcher));
        return Optional.empty();
    }

    public <T extends BaseEntity> List<T> findAll(Class<T> c) {
        T instance = createInstance(c);
        return entityRepository.findAll(Example.of(instance));
    }

    public <T extends BaseEntity> void delete(Long id, Class<T> c){
        T instance = createInstance(c);
        instance.setId(id);
        entityRepository.delete(instance);
    }
}
