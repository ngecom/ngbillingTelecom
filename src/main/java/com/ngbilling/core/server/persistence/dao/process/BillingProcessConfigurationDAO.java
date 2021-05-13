package com.ngbilling.core.server.persistence.dao.process;

import com.ngbilling.core.server.persistence.dto.process.BillingProcessConfigurationDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingProcessConfigurationDAO extends JpaRepository<BillingProcessConfigurationDTO, Integer> {

}

