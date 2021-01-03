package com.ngbilling.core.server.persistence.dao.util;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ngbilling.core.server.persistence.dto.util.LanguageDTO;


@Repository
public interface LanguageDAO extends JpaRepository<LanguageDTO, Integer>{
}
