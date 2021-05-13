package com.ngbilling.core.server.persistence.dao.util;

import com.ngbilling.core.server.persistence.dto.util.CurrencyExchangeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyExchangeDAO extends JpaRepository<CurrencyExchangeDTO, Integer>, CurrencyExchangeCustomDAO {


}
