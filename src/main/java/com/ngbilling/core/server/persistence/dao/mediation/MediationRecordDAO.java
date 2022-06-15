package com.ngbilling.core.server.persistence.dao.mediation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ngbilling.core.server.persistence.dto.mediation.MediationRecordDTO;
import com.ngbilling.core.server.persistence.dto.mediation.MediationRecordStatusDTO;

@Repository
public interface MediationRecordDAO extends JpaRepository<MediationRecordDTO, Integer> {

    @Query(value = "select mediationRecord FROM MediationRecordDTO mediationRecord "
            + " WHERE mediationRecord.key = ?1 order by mediationRecord.started desc LIMIT 1", nativeQuery = true)
    public MediationRecordDTO findNewestByKey(String key);

    /**
     * Returns true if a mediation record exists for the given id key and has been
     * processed (done).
     *
     * @param key id key of mediated record to check
     * @return true if record exists with status, false if not
     */
//    @Query("select case when (count(id_key) >0) then true else false end from mediation_record "
//            + "where id_key = ?1 and ( status_id = 29 " + // MEDIATION_RECORD_STATUS_DONE_AND_BILLABLE
//            "    or status_id = 30 " + // MEDIATION_RECORD_STATUS_DONE_AND_NOT_BILLABLE
//            ")")
//    public boolean processed(String key);

    @Query("SELECT count(distinct mediationRecord) FROM MediationRecordDTO mediationRecord "
            + " WHERE mediationRecord.process.configuration.entityId = ?1 and mediationRecord.recordStatus = ?2")
    public Long countMediationRecordsByEntityIdAndStatus(Integer entityId, MediationRecordStatusDTO status);

    @Query("select mediationRecord "
            + " FROM MediationRecordDTO mediationRecord join fetch mediationRecord.recordStatus "
            + " WHERE mediationRecord.process.id = ?1")
    public List<MediationRecordDTO> findByProcess(Integer mediationProcessId);
}
