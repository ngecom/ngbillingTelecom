package com.ngbilling.core.server.persistence.dao.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ngbilling.core.server.persistence.dto.order.OrderStatusDTO;

@Repository
public interface OrderStatusDAO extends JpaRepository<OrderStatusDTO, Integer>,OrderStatusCustomDAO{

}
