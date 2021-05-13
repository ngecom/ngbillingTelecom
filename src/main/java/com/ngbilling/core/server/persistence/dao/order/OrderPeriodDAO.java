package com.ngbilling.core.server.persistence.dao.order;

import com.ngbilling.core.server.persistence.dto.order.OrderPeriodDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderPeriodDAO extends JpaRepository<OrderPeriodDTO, Integer> {

    @Query("select p from OrderPeriodDTO p where " +
            "p.company.id=:entityId and p.periodUnit.id=:unitId and p.value=:value")
    public OrderPeriodDTO findOrderPeriod(Integer entityId, Integer value, Integer unitId);
}
