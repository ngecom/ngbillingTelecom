package com.ngbilling.core.server.persistence.dao.util;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ngbilling.core.server.persistence.dto.util.EnumerationValueDTO;

@Repository
public interface EnumerationValueDAO extends JpaRepository<EnumerationValueDTO, Integer> {

}

