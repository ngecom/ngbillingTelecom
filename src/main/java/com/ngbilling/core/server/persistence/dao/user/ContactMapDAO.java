package com.ngbilling.core.server.persistence.dao.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ngbilling.core.server.persistence.dto.contact.ContactMapDTO;

@Repository
public interface ContactMapDAO extends JpaRepository<ContactMapDTO, Integer> {

	
}
