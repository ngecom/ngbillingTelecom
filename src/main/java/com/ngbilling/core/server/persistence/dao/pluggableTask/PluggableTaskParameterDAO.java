package com.ngbilling.core.server.persistence.dao.pluggableTask;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ngbilling.core.server.persistence.dto.pluggableTask.PluggableTaskParameterDTO;

@Repository
public interface PluggableTaskParameterDAO extends JpaRepository<PluggableTaskParameterDTO, Integer> {

}
