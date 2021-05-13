package com.ngbilling.core.server.persistence.dao.util;

import com.ngbilling.core.server.persistence.dto.util.CountryDTO;
import com.ngbilling.core.server.persistence.dto.util.LanguageDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CountryDAO extends JpaRepository<CountryDTO, Integer> {
    public CountryDTO findByCode(String code);
}
