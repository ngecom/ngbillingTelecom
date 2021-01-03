package com.ngbilling.core.server.persistence.dao.util;

public interface CurrencyCustomDAO {

	public boolean findAssociationExistsForCurrency(Integer currencyId, String currencyName);
}
