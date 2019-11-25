package com.anatoliapark.nursinghome.repository.base.impl;

import com.anatoliapark.nursinghome.model.base.BaseConstantEntity;
import com.anatoliapark.nursinghome.repository.base.ConstantRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;

@Repository
public class ConstantRepositoryImpl implements ConstantRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public BaseConstantEntity findByName(String name,Class clazz) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<BaseConstantEntity> query = cb.createQuery(clazz);
        Root<BaseConstantEntity> root = query.from(clazz);

        Path<String> namePath = root.get("name");
        Predicate predicate = cb.equal(namePath, name);
        query.select(root).where(predicate);
        return entityManager.createQuery(query).getSingleResult();
    }
}
