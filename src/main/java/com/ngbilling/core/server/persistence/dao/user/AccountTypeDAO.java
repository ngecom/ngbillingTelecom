package com.ngbilling.core.server.persistence.dao.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ngbilling.core.server.persistence.dto.user.AccountTypeDTO;
@Repository
public interface AccountTypeDAO extends JpaRepository<AccountTypeDTO, Integer> {
	

}
