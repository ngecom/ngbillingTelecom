package com.ngbilling.core.server.persistence.dao.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ngbilling.core.server.persistence.dto.user.UserStatusDTO;

public interface UserStatusDAO extends JpaRepository<UserStatusDTO, Integer> {

}
 