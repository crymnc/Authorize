package com.anatoliapark.nursinghome.repository;

import com.anatoliapark.nursinghome.model.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ValueRepository extends JpaRepository<Value,Long> {

}
