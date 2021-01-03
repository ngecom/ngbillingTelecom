package com.ngbilling.core.server.persistence.dao.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ngbilling.core.server.persistence.dto.user.RoleDTO;
@Repository
public interface RoleDAO extends JpaRepository<RoleDTO, Integer> {
	
	public Optional<RoleDTO> findByRole(String name);

}
