package com.ngbilling.core.server.persistence.dao.pluggableTask;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ngbilling.core.server.persistence.dto.pluggableTask.PluggableTaskDTO;

@Repository
public interface PluggableTaskDAO extends JpaRepository<PluggableTaskDTO, Integer> {

    @Query("SELECT b FROM PluggableTaskDTO b WHERE b.entityId = :entityId AND b.type.category.id = :categoryId ORDER BY b.processingOrder")
    public List<PluggableTaskDTO> findByEntityCategory(Integer entityId, Integer categoryId);

    @Query("SELECT b FROM PluggableTaskDTO b WHERE b.entityId = :entityId AND b.type.id = :typeId")
    public PluggableTaskDTO findByEntityType(Integer entityId, Integer typeId);


}
