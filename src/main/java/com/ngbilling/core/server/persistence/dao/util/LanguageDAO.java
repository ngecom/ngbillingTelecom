package com.ngbilling.core.server.persistence.dao.util;

import com.ngbilling.core.payload.request.util.ComboReferenceInput;
import com.ngbilling.core.server.persistence.dto.util.LanguageDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LanguageDAO extends JpaRepository<LanguageDTO, Integer> {
    public LanguageDTO findByCode(String code);
    @Query("select new com.ngbilling.core.payload.request.util.ComboReferenceInput(u.id,u.code,u.description) from LanguageDTO u")
    public List<ComboReferenceInput> findAllLanguages();
}
