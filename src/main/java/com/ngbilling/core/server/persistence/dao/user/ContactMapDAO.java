package com.ngbilling.core.server.persistence.dao.user;

import com.ngbilling.core.server.persistence.dto.contact.ContactMapDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactMapDAO extends JpaRepository<ContactMapDTO, Integer> {


}
