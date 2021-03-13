package com.ngbilling.core.server.service.util.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.ngbilling.core.server.persistence.dao.util.CurrencyDAO;
import com.ngbilling.core.server.persistence.dao.util.JbillingTableDAO;
import com.ngbilling.core.server.persistence.dao.util.LanguageDAO;
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

}
