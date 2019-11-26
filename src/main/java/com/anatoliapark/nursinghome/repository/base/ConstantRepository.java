package com.anatoliapark.nursinghome.repository.base;

import com.anatoliapark.nursinghome.model.base.BaseConstantEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface ConstantRepository {

    <T extends BaseConstantEntity> T findByName(String name, Class<T> c);

    <T extends BaseConstantEntity> T find(Long id, Class<T> c);

    <T extends BaseConstantEntity> Long delete(Long id, Class<T> c);

    <T extends BaseConstantEntity> List<T> findAll(Class<T> c);

    <T extends BaseConstantEntity> T save(T constantEntity);
}
