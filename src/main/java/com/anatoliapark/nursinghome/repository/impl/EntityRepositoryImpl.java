package com.anatoliapark.nursinghome.repository.impl;

import com.anatoliapark.nursinghome.model.base.BaseEntity;
import com.anatoliapark.nursinghome.repository.EntityRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class EntityRepositoryImpl implements EntityRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public <T extends BaseEntity> T save(T constantEntity) {
        if (constantEntity.isNew()) {
            this.entityManager.persist(constantEntity);
            return constantEntity;
        } else {
            return this.entityManager.merge(constantEntity);
        }
    }

    @Override
    public <T extends BaseEntity> List<T> findBy(Map<String, String> columnNameValuePair,Class<T> c) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(c);
        Root<T> root = query.from(c);
        List<Predicate> predicates = new ArrayList<>();
        for(Map.Entry<String, String> entry : columnNameValuePair.entrySet()){
            if(entry.getValue()!=null) {
                predicates.add(builder.and(builder.equal(root.get(entry.getKey()), entry.getValue())));
            }
        }
        query.where(predicates.toArray(new Predicate[predicates.size()]));
        return this.entityManager.createQuery(query).getResultList();


    }

    @Override
    public <T extends BaseEntity> T find(Long id, Class<T> c) {
        return this.entityManager.find(c,id);
    }

    @Override
    public <T extends BaseEntity> void delete(T constantEntity) {
        this.entityManager.remove(constantEntity);
    }

    @Override
    public <T extends BaseEntity> List<T> findAll(Class<T> c) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(c);
        Root<T> root = query.from(c);
        query.select(root);
        return this.entityManager.createQuery(query).getResultList();
    }

}


