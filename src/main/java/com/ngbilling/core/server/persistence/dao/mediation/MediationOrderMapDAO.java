package com.ngbilling.core.server.persistence.dao.mediation;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ngbilling.core.server.persistence.dto.mediation.MediationOrderMap;

public interface MediationOrderMapDAO extends JpaRepository<MediationOrderMap, Integer> {

}
