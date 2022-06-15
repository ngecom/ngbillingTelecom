package com.ngbilling.core.server.persistence.dao.process;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ngbilling.core.server.persistence.dto.process.BillingProcessConfigurationDTO;

@Repository
public interface BillingProcessConfigurationDAO extends JpaRepository<BillingProcessConfigurationDTO, Integer> {

}

