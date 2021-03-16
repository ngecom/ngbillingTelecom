package com.ngbilling.core.server.service.util;

import com.ngbilling.core.server.persistence.dto.user.CompanyDTO;
import com.ngbilling.core.server.persistence.dto.user.UserDTO;
import com.ngbilling.core.server.persistence.dto.util.CurrencyDTO;
import com.ngbilling.core.server.persistence.dto.util.JbillingTable;
import com.ngbilling.core.server.persistence.dto.util.LanguageDTO;

public interface UtilService {

	public LanguageDTO findByLanguageCode(String code);
	public CurrencyDTO findByCurrencyCode(String code);
	public JbillingTable findByName(String tableName);
	public void initEntityDefault(CompanyDTO company, UserDTO rootUser, LanguageDTO language);
	
}
