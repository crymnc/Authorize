package com.anatoliapark.nursinghome.repository;

import com.anatoliapark.nursinghome.model.Variable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface VariableRepository extends JpaRepository<Variable, Long> {
}
