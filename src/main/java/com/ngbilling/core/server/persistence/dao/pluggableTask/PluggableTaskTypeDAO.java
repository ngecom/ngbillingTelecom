package com.ngbilling.core.server.persistence.dao.pluggableTask;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ngbilling.core.server.persistence.dto.pluggableTask.PluggableTaskTypeDTO;

public interface PluggableTaskTypeDAO extends JpaRepository<PluggableTaskTypeDTO, Integer>{

	
}
