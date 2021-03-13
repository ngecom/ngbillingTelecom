package com.ngbilling.core.server.persistence.dao.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ngbilling.core.server.persistence.dto.user.RoleDTO;
@Repository
public interface RoleDAO extends JpaRepository<RoleDTO, Integer> {
	
	public Optional<RoleDTO> findByRole(String name);
	
	@Query(value="select * from role a where a.role_type_id= :roleTypeId and a.entity_id= :companyId", nativeQuery=true)
	public List<RoleDTO> findRoleTypeIdAndCompanyId(Integer roleTypeId, Integer companyId);

}
