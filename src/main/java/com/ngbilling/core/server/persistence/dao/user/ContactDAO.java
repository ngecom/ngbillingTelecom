package com.ngbilling.core.server.persistence.dao.user;

import com.ngbilling.core.server.persistence.dto.contact.ContactDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactDAO extends JpaRepository<ContactDTO, Integer> {

    @Query("SELECT distinct c "
            + "  FROM ContactDTO c, JbillingTable d "
            + " WHERE c.contactMap.jbillingTable.id = d.id "
            + "   AND d.name = ?1 "
            + "   AND c.contactMap.foreignId = ?2 ")
    public ContactDTO findContact(String tableName, Integer userId);
}
