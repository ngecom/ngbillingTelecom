package com.ngbilling.core.server.persistence.dao.util;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ngbilling.core.server.persistence.dto.util.EnumerationDTO;

@Repository
public interface EnumerationDAO extends JpaRepository<EnumerationDTO, Integer>{

}
