package com.ngbilling.core.server.persistence.dao.mediation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ngbilling.core.server.persistence.dto.mediation.MediationConfiguration;

public interface MediationConfigurationDAO extends JpaRepository<MediationConfiguration, Integer> {

	@Query("SELECT b FROM MediationConfiguration b WHERE b.entityId = ? ORDER BY orderValue")
	public List<MediationConfiguration> findAllByEntity(Integer entityId);

}
