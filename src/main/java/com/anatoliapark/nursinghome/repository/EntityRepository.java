package com.anatoliapark.nursinghome.repository;

import com.anatoliapark.nursinghome.model.base.BaseEntity;

import java.util.List;
import java.util.Map;


public interface EntityRepository {

    <T extends BaseEntity> List<T> findBy(Map<String, String> columnNameValuePair,Class<T> c);

    <T extends BaseEntity> T find(Long id, Class<T> c);

    <T extends BaseEntity> void delete(T constantEntity);

    <T extends BaseEntity> List<T> findAll(Class<T> c);

    <T extends BaseEntity> T save(T constantEntity);



}
