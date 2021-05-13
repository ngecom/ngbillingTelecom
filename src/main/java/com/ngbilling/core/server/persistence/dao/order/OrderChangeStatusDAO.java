package com.ngbilling.core.server.persistence.dao.order;

import com.ngbilling.core.server.persistence.dto.order.OrderChangeStatusDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderChangeStatusDAO extends JpaRepository<OrderChangeStatusDTO, Integer> {

    @Query("select max(id) from OrderChangeStatusDTO u")
    public Integer getMaxStatusId();
}