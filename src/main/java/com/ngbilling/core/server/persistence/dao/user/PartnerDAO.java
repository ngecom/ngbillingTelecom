package com.ngbilling.core.server.persistence.dao.user;

import com.ngbilling.core.server.persistence.dto.partner.PartnerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerDAO extends JpaRepository<PartnerDTO, Integer> {

}
