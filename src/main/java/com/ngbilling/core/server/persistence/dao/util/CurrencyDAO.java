package com.ngbilling.core.server.persistence.dao.util;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ngbilling.core.server.persistence.dto.util.CurrencyDTO;

@Repository
public interface CurrencyDAO extends JpaRepository<CurrencyDTO, Integer>, CurrencyCustomDAO{ 

	public boolean findAssociationExistsForCurrency(Integer currencyId, String currencyName);

	public CurrencyDTO findByCode(String code);
}
