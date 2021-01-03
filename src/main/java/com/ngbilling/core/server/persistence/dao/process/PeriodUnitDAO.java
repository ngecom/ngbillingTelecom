package com.ngbilling.core.server.persistence.dao.process;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ngbilling.core.server.persistence.dto.process.PeriodUnitDTO;
@Repository
public interface PeriodUnitDAO extends JpaRepository<PeriodUnitDTO, Integer> {
	

}
