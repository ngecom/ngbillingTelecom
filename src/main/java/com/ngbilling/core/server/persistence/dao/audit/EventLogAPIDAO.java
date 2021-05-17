package com.ngbilling.core.server.persistence.dao.audit;

import com.ngbilling.core.server.persistence.dto.audit.EventLogAPIDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventLogAPIDAO extends JpaRepository<EventLogAPIDTO, Integer> {

}
