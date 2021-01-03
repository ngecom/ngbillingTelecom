package com.ngbilling.core.server.persistence.dao.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ngbilling.core.server.persistence.dto.order.OrderLineTypeDTO;

@Repository
public interface OrderLineTypeDAO extends JpaRepository<OrderLineTypeDTO, Integer>{

}
