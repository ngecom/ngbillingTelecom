package com.ngbilling.core.server.persistence.dao.user;

import com.ngbilling.core.server.persistence.dto.user.UserStatusDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStatusDAO extends JpaRepository<UserStatusDTO, Integer> {

}
 