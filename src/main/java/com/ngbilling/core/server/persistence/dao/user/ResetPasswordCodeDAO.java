package com.ngbilling.core.server.persistence.dao.user;

import com.ngbilling.core.server.persistence.dto.user.ResetPasswordCodeDTO;
import com.ngbilling.core.server.persistence.dto.user.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ResetPasswordCodeDAO extends JpaRepository<ResetPasswordCodeDTO, Integer> {

    @Query("select u from ResetPasswordCodeDTO u where u.user = :user")
    public ResetPasswordCodeDTO findByUser(UserDTO user);

}
