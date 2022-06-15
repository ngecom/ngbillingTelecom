package com.ngbilling.core.server.persistence.dao.audit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ngbilling.core.server.persistence.dto.audit.EventLogAPIDTO;

@Repository
public interface EventLogAPIDAO extends JpaRepository<EventLogAPIDTO, Integer> {

}
