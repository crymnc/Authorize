package com.anatoliapark.nursinghome.repository;

import com.anatoliapark.nursinghome.model.base.BaseConstantEntity;
import com.anatoliapark.nursinghome.repository.impl.ConstantRepositoryImpl;

import java.util.List;


public interface ConstantRepository {

    <T extends BaseConstantEntity> T findByName(String name, Class<T> c);

    <T extends BaseConstantEntity> T find(Long id, Class<T> c);

    <T extends BaseConstantEntity> void delete(T constantEntity);

    <T extends BaseConstantEntity> List<T> findAll(Class<T> c);

    <T extends BaseConstantEntity> List<T> findAll(Class<T> c, ConstantRepositoryImpl.SortOrder sortOrder);

    <T extends BaseConstantEntity> T save(T constantEntity);
}
