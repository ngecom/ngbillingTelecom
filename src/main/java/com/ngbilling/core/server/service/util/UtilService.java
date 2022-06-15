package com.ngbilling.core.server.service.util;

import java.util.List;
import java.util.Locale;

import com.ngbilling.core.payload.request.util.ComboReferenceInput;
import com.ngbilling.core.server.persistence.dto.audit.EventLogAPIDTO;
import com.ngbilling.core.server.persistence.dto.user.UserDTO;
import com.ngbilling.core.server.persistence.dto.util.CountryDTO;
import com.ngbilling.core.server.persistence.dto.util.CurrencyDTO;
import com.ngbilling.core.server.persistence.dto.util.JbillingTable;
import com.ngbilling.core.server.persistence.dto.util.LanguageDTO;

public interface UtilService {

    public LanguageDTO findByLanguageCode(String code);

    public CurrencyDTO findByCurrencyCode(String code);

    public JbillingTable findByName(String tableName);

    public List<CountryDTO> findAllCountries(Integer languageId);

    public List<ComboReferenceInput> findAllCurrencies(Integer languageId);

    public List<ComboReferenceInput> findAllLanguages(Integer languageId);

    public void initEntityDefault(UserDTO rootUser, Locale locale);

    public EventLogAPIDTO createEventLogAPI(EventLogAPIDTO eventLogAPIDTO);

    public String getDescription(String table,Integer foreignId,String label,Integer languageId);

    public void setDescription(String tableName, Integer foreignId, String label,Integer languageId,String content);
    
    public boolean isAllowSignup(Integer languageId);

    public List<ComboReferenceInput> findEntityAccountTypes(Integer entityId);
}
