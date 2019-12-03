package com.anatoliapark.nursinghome.repository.impl;

import com.anatoliapark.nursinghome.model.base.BaseConstantEntity;
import com.anatoliapark.nursinghome.repository.ConstantRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class ConstantRepositoryImpl implements ConstantRepository {
    public enum SortOrder {
        ASC,
        DESC
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public <T extends BaseConstantEntity> T findByName(String name, Class<T> c) {
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(c);
        Root<T> root = query.from(c);
        Path<String> namePath = root.get("name");
        Predicate predicate = cb.equal(namePath, name);
        query.select(root).where(predicate);
        return entityManager.createQuery(query).getSingleResult();
    }

    @Override
    @Transactional
    public <T extends BaseConstantEntity> T save(T constantEntity) {
        if (constantEntity.isNew()) {
            this.entityManager.persist(constantEntity);
            return constantEntity;
        } else {
            return this.entityManager.merge(constantEntity);
        }
    }

    @Override
    public <T extends BaseConstantEntity> T find(Long id, Class<T> c) {
        return this.entityManager.find(c,id);
    }

    @Override
    public <T extends BaseConstantEntity> void delete(T constantEntity) {
        this.entityManager.remove(constantEntity);
    }

    @Override
    public <T extends BaseConstantEntity> List<T> findAll(Class<T> c) {
        return findAll(c,null);
    }

    @Override
    public <T extends BaseConstantEntity> List<T> findAll(Class<T> c, SortOrder sortOrder) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(c);
        Root<T> root = query.from(c);
        query.select(root);
        if (sortOrder != null) {
            if(sortOrder == SortOrder.ASC)
                query.orderBy(builder.asc(root.get("name")));
            else
                query.orderBy(builder.desc(root.get("name")));
        }
        return this.entityManager.createQuery(query).getResultList();
    }
}


