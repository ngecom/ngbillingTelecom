package com.ngbilling.core.server.persistence.dao.util;

import com.ngbilling.core.server.persistence.dto.util.EnumerationDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnumerationDAO extends JpaRepository<EnumerationDTO, Integer> {

}

