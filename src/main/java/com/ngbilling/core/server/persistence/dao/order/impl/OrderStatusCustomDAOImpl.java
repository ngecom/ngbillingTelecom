package com.ngbilling.core.server.persistence.dao.order.impl;

import com.ngbilling.core.payload.request.order.OrderStatusFlag;
import com.ngbilling.core.server.persistence.dao.AbstractJpaDAO;
import com.ngbilling.core.server.persistence.dao.order.OrderStatusCustomDAO;
import com.ngbilling.core.server.persistence.dto.order.OrderStatusDTO;

public class OrderStatusCustomDAOImpl extends AbstractJpaDAO<OrderStatusDTO> implements OrderStatusCustomDAO {


    @Override
    public Integer getDefaultOrderStatusId(OrderStatusFlag flag, Integer entityId) {
        return null;
    }
}
