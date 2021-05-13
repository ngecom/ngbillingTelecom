package com.ngbilling.core.server.persistence.dao.util;

import com.ngbilling.core.server.persistence.dto.util.EnumerationValueDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnumerationValueDAO extends JpaRepository<EnumerationValueDTO, Integer> {

}

