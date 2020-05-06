package com.anatoliapark.nursinghome.repository;

import com.anatoliapark.nursinghome.model.base.BaseConstantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface ConstantRepository<T extends BaseConstantEntity> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {
}
