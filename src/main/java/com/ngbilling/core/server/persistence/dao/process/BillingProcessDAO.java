package com.ngbilling.core.server.persistence.dao.process;

import java.util.Date;

import org.hibernate.ScrollableResults;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ngbilling.core.server.persistence.dto.process.BillingProcessDTO;

@Repository
public interface BillingProcessDAO extends JpaRepository<BillingProcessDTO, Integer> {

    @Query(" SELECT a.id FROM UserDTO a, OrderDTO o WHERE a.id = o.baseUserByUserId.id  AND ( o.nextBillableDay is null " +
            "     or cast(o.nextBillableDay as date) <= :dueDate " +
            " ) " +
            " AND o.deleted = 0 " +
            " AND a.company.id = :entity ")
    public ScrollableResults findBillableUsersToProcess(int entity, Date dueDate);

}
