package com.ngbilling.core.server.persistence.dao.item.impl;

import javax.persistence.TypedQuery;

import com.ngbilling.core.server.persistence.dao.AbstractJpaDAO;
import com.ngbilling.core.server.persistence.dao.util.CurrencyCustomDAO;
import com.ngbilling.core.server.persistence.dto.order.OrderDTO;
import com.ngbilling.core.server.persistence.dto.util.CurrencyDTO;

public class CurrencyCustomDAOImpl extends AbstractJpaDAO<CurrencyDTO> implements CurrencyCustomDAO{

	@Override
	public boolean findAssociationExistsForCurrency(Integer currencyId, String currencyName) {
		// TODO Auto-generated method stub
		TypedQuery<OrderDTO> query= getEntityManager().createQuery("select u from CurrencyDTO u where "+ currencyName + ".id = ?1", OrderDTO.class
	            );
		
		 query.setParameter(1,  currencyId);
		return query.getSingleResult() != null;
	}

}
