package com.ngbilling.core.server.persistence.dao.mediation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ngbilling.core.server.persistence.dto.mediation.MediationProcess;

@Repository
public interface MediationProcessDAO extends JpaRepository<MediationProcess, Integer> {

    @Query("SELECT process FROM MediationProcess process WHERE process.configuration.entityId = ?1 order by id desc")
    public List<MediationProcess> findAllByEntity(Integer entityId);

    @Query("SELECT case when (count(process.id) >0) then true else false end FROM MediationProcess process WHERE process.configuration.entityId = ?1 and process.endDatetime is null")
    public boolean isProcessing(Integer entityId);

    @Query("SELECT case when (count(process.id) >0) then true else false end FROM MediationProcess process WHERE process.configuration.id = ?1 and process.endDatetime is null")
    public boolean isConfigurationProcessing(Integer configurationId);

    @Query(value = "SELECT * FROM MediationProcess process WHERE process.configuration.entityId = ?1 order by process.startDatetime DESC LIMIT 1", nativeQuery = true)
    public MediationProcess getLatestMediationProcess(Integer entityId);

    @Query("SELECT case when (count(record.id) >0) then true else false end "
            + "FROM MediationRecordDTO record WHERE record.process.id = ?1 and (record.recordStatus.id = 3"
            + " or record.recordStatus.id =4" + ")")
    public boolean isMediationProcessHasFailedRecords(Integer mediationProcessId);

}
