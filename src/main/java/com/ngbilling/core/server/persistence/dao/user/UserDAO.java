package com.ngbilling.core.server.persistence.dao.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ngbilling.core.server.persistence.dto.user.CompanyDTO;
import com.ngbilling.core.server.persistence.dto.user.UserDTO;
@Repository
public interface UserDAO extends JpaRepository<UserDTO, Integer> {
	@Query("SELECT u.company FROM UserDTO u WHERE u.id = ?1")
	public CompanyDTO getEntity (Integer userId);
	
	public Optional<UserDTO> findByUserName(String username);

	public Boolean existsByUserName(String username);

}
