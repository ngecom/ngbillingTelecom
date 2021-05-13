package com.ngbilling.core.server.persistence.dao.order;

import com.ngbilling.core.server.persistence.dto.order.OrderStatusDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusDAO extends JpaRepository<OrderStatusDTO, Integer>, OrderStatusCustomDAO {

}
