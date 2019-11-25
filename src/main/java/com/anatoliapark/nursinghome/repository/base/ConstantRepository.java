package com.anatoliapark.nursinghome.repository.base;

import com.anatoliapark.nursinghome.model.base.BaseConstantEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface ConstantRepository {
    <T extends BaseConstantEntity> T findByName(String name, Class<T> clazz);
}
