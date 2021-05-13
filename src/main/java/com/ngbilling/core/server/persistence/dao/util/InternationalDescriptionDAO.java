package com.ngbilling.core.server.persistence.dao.util;

import com.ngbilling.core.server.persistence.dto.util.InternationalDescriptionDTO;
import com.ngbilling.core.server.persistence.dto.util.InternationalDescriptionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InternationalDescriptionDAO extends JpaRepository<InternationalDescriptionDTO, InternationalDescriptionId>, InternationalDescriptionCustomDAO {

    @Query("SELECT t FROM InternationalDescriptionDTO t where t.id.tableId = ?1 AND t.id.foreignId = ?2 AND t.id.psudoColumn = ?3 AND t.id.languageId = ?4")
    public InternationalDescriptionDTO findIt(Integer table, Integer foreignId, String column, Integer language);

}
