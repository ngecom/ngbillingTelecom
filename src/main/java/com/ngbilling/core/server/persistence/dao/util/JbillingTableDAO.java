package com.ngbilling.core.server.persistence.dao.util;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ngbilling.core.server.persistence.dto.util.JbillingTable;


@Repository
public interface JbillingTableDAO extends JpaRepository<JbillingTable, Integer> {

    @Query("select u from JbillingTable u where u.name = :tableName")
    public JbillingTable findByName(String tableName);
}
