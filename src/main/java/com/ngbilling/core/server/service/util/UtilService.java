package com.ngbilling.core.server.service.util;

import com.ngbilling.core.server.persistence.dto.audit.EventLogAPIDTO;
import com.ngbilling.core.server.persistence.dto.user.UserDTO;
import com.ngbilling.core.server.persistence.dto.util.CountryDTO;
import com.ngbilling.core.server.persistence.dto.util.CurrencyDTO;
import com.ngbilling.core.server.persistence.dto.util.JbillingTable;
import com.ngbilling.core.server.persistence.dto.util.LanguageDTO;

import java.util.List;
import java.util.Locale;

public interface UtilService {

    public LanguageDTO findByLanguageCode(String code);

    public CurrencyDTO findByCurrencyCode(String code);

    public JbillingTable findByName(String tableName);

    public List<CountryDTO> findAllCountries(Integer languageId);

    public List<Object[]> findAllCurrencies(Integer languageId);

    public List<Object> findAllLanguages(Integer languageId);

    public void initEntityDefault(UserDTO rootUser, Locale locale);

    public EventLogAPIDTO createEventLogAPI(EventLogAPIDTO eventLogAPIDTO);

    public String getDescription(String table,Integer foreignId,String label,Integer languageId);

    public void setDescription(String tableName, Integer foreignId, String label,Integer languageId,String content);
}
