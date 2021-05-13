package com.ngbilling.core.server.persistence.dao.user;

import com.ngbilling.core.server.persistence.dto.user.AccountTypeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTypeDAO extends JpaRepository<AccountTypeDTO, Integer> {


}
