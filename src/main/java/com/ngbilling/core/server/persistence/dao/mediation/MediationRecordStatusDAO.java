package com.ngbilling.core.server.persistence.dao.mediation;

import com.ngbilling.core.server.persistence.dto.mediation.MediationRecordStatusDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediationRecordStatusDAO extends JpaRepository<MediationRecordStatusDTO, Integer> {

}
