package com.ngbilling.core.server.persistence.dao.process;

import com.ngbilling.core.server.persistence.dto.process.PeriodUnitDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeriodUnitDAO extends JpaRepository<PeriodUnitDTO, Integer> {


}
