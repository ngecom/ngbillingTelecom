package com.ngbilling.core.server.persistence.dao.user;

import com.ngbilling.core.server.persistence.dto.user.AccountTypeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountTypeDAO extends JpaRepository<AccountTypeDTO, Integer> {

    @Query("select u.id,'' from AccountTypeDTO u where u.company.id = :entityId")
    public List<Object[]> findEntityAccountTypes(Integer entityId);

}
