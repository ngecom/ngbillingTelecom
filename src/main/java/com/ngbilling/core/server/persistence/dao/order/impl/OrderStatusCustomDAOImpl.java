package com.ngbilling.core.server.persistence.dao.order.impl;

import com.ngbilling.core.payload.request.order.OrderStatusFlag;
import com.ngbilling.core.server.persistence.dao.AbstractJpaDAO;
import com.ngbilling.core.server.persistence.dao.order.OrderStatusCustomDAO;
import com.ngbilling.core.server.persistence.dao.order.OrderStatusDAO;
import com.ngbilling.core.server.persistence.dto.order.OrderStatusDTO;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public class OrderStatusCustomDAOImpl extends AbstractJpaDAO<OrderStatusDTO> implements OrderStatusCustomDAO {


    @Override
    public Integer getDefaultOrderStatusId(OrderStatusFlag flag, Integer entityId) {
        return null;
    }
}
