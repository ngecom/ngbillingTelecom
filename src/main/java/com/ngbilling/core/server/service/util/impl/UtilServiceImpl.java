package com.ngbilling.core.server.service.util.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.ngbilling.core.payload.request.order.OrderStatusFlag;
import com.ngbilling.core.server.persistence.dao.order.OrderStatusDAO;
import com.ngbilling.core.server.persistence.dao.util.CurrencyDAO;
import com.ngbilling.core.server.persistence.dao.util.JbillingTableDAO;
import com.ngbilling.core.server.persistence.dao.util.LanguageDAO;
import com.ngbilling.core.server.persistence.dto.order.OrderStatusDTO;
import com.ngbilling.core.server.persistence.dto.user.CompanyDTO;
import com.ngbilling.core.server.persistence.dto.user.UserDTO;
import com.ngbilling.core.server.persistence.dto.util.CurrencyDTO;
import com.ngbilling.core.server.persistence.dto.util.JbillingTable;
import com.ngbilling.core.server.persistence.dto.util.LanguageDTO;
import com.ngbilling.core.server.service.util.UtilService;

public class UtilServiceImpl implements UtilService{

	@Autowired
	private LanguageDAO languageDAO;
	
	@Autowired
	private CurrencyDAO currencyDAO;
	
	@Autowired
	private JbillingTableDAO jbillingTableDAO;
	
	@Autowired
	private OrderStatusDAO orderStatusDAO;
	
	@Override
	public LanguageDTO findByLanguageCode(String code) {
		return languageDAO.findByCode(code);
	}

	@Override
	public CurrencyDTO findByCurrencyCode(String code) {
		return currencyDAO.findByCode(code);
	}

	@Override
	public JbillingTable findByName(String tableName) {
		// TODO Auto-generated method stub
		return jbillingTableDAO.findByName(tableName);
	}

	@Override
	public void initEntityDefault(CompanyDTO company, UserDTO rootUser, LanguageDTO language) {
		OrderStatusDTO invoiceOS = new OrderStatusDTO();
		invoiceOS.setOrderStatusFlag(OrderStatusFlag.INVOICE);
		invoiceOS.setEntity(company);
		invoiceOS.setDescription("Active", language.getId());
		orderStatusDAO.save(invoiceOS);
		invoiceOS = new OrderStatusDTO();
		invoiceOS.setOrderStatusFlag(OrderStatusFlag.FINISHED);
		invoiceOS.setEntity(company);
		invoiceOS.setDescription("Finished", language.getId());
		orderStatusDAO.save(invoiceOS);
		invoiceOS = new OrderStatusDTO();
		invoiceOS.setOrderStatusFlag(OrderStatusFlag.NOT_INVOICE);
		invoiceOS.setEntity(company);
		invoiceOS.setDescription("Suspended", language.getId());
		orderStatusDAO.save(invoiceOS);
		invoiceOS = new OrderStatusDTO();
		invoiceOS.setOrderStatusFlag(OrderStatusFlag.SUSPENDED_AGEING);
		invoiceOS.setEntity(company);
		invoiceOS.setDescription("Suspended ageing(auto)", language.getId());
		orderStatusDAO.save(invoiceOS);	        

		
	}

}
