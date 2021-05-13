package com.ngbilling.core.server.persistence.dao.util;

import com.ngbilling.core.server.persistence.dto.util.CurrencyExchangeDTO;

import java.util.Date;
import java.util.List;

public interface CurrencyExchangeCustomDAO {

    public CurrencyExchangeDTO findExchange(Integer entityId, Integer currencyId);

    public List<CurrencyExchangeDTO> findByEntity(Integer entityId);

    public CurrencyExchangeDTO getExchangeRateForRange(Integer entityId, Integer currencyId, Date from, Date to);

    public CurrencyExchangeDTO getExchangeRateForDate(Integer entityId, Integer currencyId, Date forDate);
}
