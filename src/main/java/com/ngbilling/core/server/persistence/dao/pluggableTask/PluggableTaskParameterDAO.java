package com.ngbilling.core.server.persistence.dao.pluggableTask;

import com.ngbilling.core.server.persistence.dto.pluggableTask.PluggableTaskParameterDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PluggableTaskParameterDAO extends JpaRepository<PluggableTaskParameterDTO, Integer> {

}
