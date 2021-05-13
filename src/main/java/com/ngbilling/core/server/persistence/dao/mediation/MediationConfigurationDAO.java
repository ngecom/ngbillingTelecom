package com.ngbilling.core.server.persistence.dao.mediation;

import com.ngbilling.core.server.persistence.dto.mediation.MediationConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MediationConfigurationDAO extends JpaRepository<MediationConfiguration, Integer> {

    @Query("SELECT b FROM MediationConfiguration b WHERE b.entityId = ?1 ORDER BY orderValue")
    public List<MediationConfiguration> findAllByEntity(Integer entityId);

}
