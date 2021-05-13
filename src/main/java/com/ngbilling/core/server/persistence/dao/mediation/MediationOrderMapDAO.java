package com.ngbilling.core.server.persistence.dao.mediation;

import com.ngbilling.core.server.persistence.dto.mediation.MediationOrderMap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediationOrderMapDAO extends JpaRepository<MediationOrderMap, Integer> {

}
