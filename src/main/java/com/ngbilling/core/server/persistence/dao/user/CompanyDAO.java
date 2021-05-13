package com.ngbilling.core.server.persistence.dao.user;

import com.ngbilling.core.server.persistence.dto.user.CompanyDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyDAO extends JpaRepository<CompanyDTO, Integer> {

    public CompanyDTO findByDescription(String companyName);
}
