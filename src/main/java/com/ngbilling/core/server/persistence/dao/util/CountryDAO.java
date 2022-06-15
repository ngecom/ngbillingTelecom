package com.ngbilling.core.server.persistence.dao.util;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ngbilling.core.server.persistence.dto.util.CountryDTO;


@Repository
public interface CountryDAO extends JpaRepository<CountryDTO, Integer> {
    public CountryDTO findByCode(String code);
}
