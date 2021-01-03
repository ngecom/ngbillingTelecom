package com.ngbilling.core.server.persistence.dao.item.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;

import com.ngbilling.core.server.persistence.dao.AbstractJpaDAO;
import com.ngbilling.core.server.persistence.dao.util.CurrencyExchangeCustomDAO;
import com.ngbilling.core.server.persistence.dto.util.CurrencyExchangeDTO;

public class CurrencyExchangeCustomDAOImpl extends AbstractJpaDAO<CurrencyExchangeDTO> implements CurrencyExchangeCustomDAO{

	private static final String FIND_EXCHANGE_FOR_DATE =
	        "SELECT a " +
	        "  FROM CurrencyExchangeDTO a " +
	        " WHERE a.entityId = ?1 " +
	        "   AND a.currency.id = ?2 " +
	        "   AND a.validSince <= ?3 ORDER BY a.validSince DESC";

	    private static final String FIND_EXCHANGE_IN_RANGE =
	        "SELECT a " +
	        "  FROM CurrencyExchangeDTO a " +
	        " WHERE a.entityId = ?1 " +
	        "   AND a.currency.id = ?2 " +
	        "   AND a.validSince >= ?3 " +
	        "   AND a.validSince <= ?4 ORDER BY a.validSince DESC";

	    private static final String  FIND_BY_ENTITY =
	        " SELECT a " +
	        "   FROM CurrencyExchangeDTO a " +
	        "  WHERE a.entityId = ?1";
	    @Override
	    public  List<CurrencyExchangeDTO> findByEntity(Integer entityId) 
	    {
	    TypedQuery<CurrencyExchangeDTO> query = getEntityManager().createQuery(FIND_BY_ENTITY,
	    		CurrencyExchangeDTO.class);
		
		return query.setParameter(1,  entityId).getResultList();
	    }
		@Override
	    public CurrencyExchangeDTO getExchangeRateForRange(Integer entityId, Integer currencyId, Date from, Date to) 
	    {
	    	TypedQuery<CurrencyExchangeDTO> query = getEntityManager().createQuery(FIND_EXCHANGE_IN_RANGE,
		    		CurrencyExchangeDTO.class);
	    	query.setParameter(1,entityId);
	    	query.setParameter(2,currencyId);
	    	query.setParameter(3,from);
	    	query.setParameter(4,to);
	    	if (query.getResultList() == null || query.getResultList().size() == 0)
	    	{
	    		return null;
	    		
	    	}
	    	return query.getResultList().get(0);
	    	
	    }
		@Override
		public CurrencyExchangeDTO findExchange(Integer entityId, Integer currencyId) {
			// TODO Auto-generated method stub
			return getExchangeRateForDate(entityId, currencyId, new Date());
			//return null;
		}
		@Override
		public CurrencyExchangeDTO getExchangeRateForDate(Integer entityId, Integer currencyId, Date forDate) {
	        TypedQuery<CurrencyExchangeDTO> query = getEntityManager().createQuery(FIND_EXCHANGE_FOR_DATE,
		    		CurrencyExchangeDTO.class);
	        query.setParameter(1,entityId);
	    	query.setParameter(2,currencyId);
	    	query.setParameter(3,forDate);
	    	if (query.getResultList() == null || query.getResultList().size() == 0)
	    	{
	    		return null;
	    		
	    	}
	    	return query.getResultList().get(0);
	    }
		
		
	    
	    
}
