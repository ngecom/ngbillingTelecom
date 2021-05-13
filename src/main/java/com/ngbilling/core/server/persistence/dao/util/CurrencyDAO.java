package com.ngbilling.core.server.persistence.dao.util;

import com.ngbilling.core.server.persistence.dto.util.CurrencyDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyDAO extends JpaRepository<CurrencyDTO, Integer>, CurrencyCustomDAO {

    public boolean findAssociationExistsForCurrency(Integer currencyId, String currencyName);

    public CurrencyDTO findByCode(String code);

    @Query("select u.id,u.code,'' from CurrencyDTO u")
    public List<Object[]> findAllCurrencies();
}
