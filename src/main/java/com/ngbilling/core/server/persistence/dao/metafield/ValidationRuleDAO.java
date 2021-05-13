package com.ngbilling.core.server.persistence.dao.metafield;

import com.ngbilling.core.server.persistence.dto.metafield.ValidationRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ValidationRuleDAO extends JpaRepository<ValidationRule, Integer> {

}
