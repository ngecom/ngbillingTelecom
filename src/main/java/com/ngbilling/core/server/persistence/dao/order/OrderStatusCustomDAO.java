package com.ngbilling.core.server.persistence.dao.order;

import com.ngbilling.core.payload.request.order.OrderStatusFlag;

public interface OrderStatusCustomDAO {

	public Integer getDefaultOrderStatusId(OrderStatusFlag flag, Integer entityId);
}
