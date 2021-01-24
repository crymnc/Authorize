package com.anatoliapark.nursinghome.service;

import com.anatoliapark.nursinghome.entity.base.BaseConstantEntity;
import com.anatoliapark.nursinghome.service.base.EntityService;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class ConstantService extends EntityService {

    public <T extends BaseConstantEntity> T find(String name, Class<T> c) {
        T instance = createInstance(c);
        instance.setName(name);
        return (T)entityRepository.findOne(Example.of(instance)).get();
    }

}
