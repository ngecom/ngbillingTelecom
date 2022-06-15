package com.ngbilling.core.server.persistence.dao.metafield;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ngbilling.core.server.persistence.dto.metafield.ValidationRule;

@Repository

public interface ValidationRuleDAO extends JpaRepository<ValidationRule, Integer> {

}
