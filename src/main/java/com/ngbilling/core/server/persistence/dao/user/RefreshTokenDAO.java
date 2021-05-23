package com.ngbilling.core.server.persistence.dao.user;

import com.ngbilling.core.server.persistence.dto.user.RefreshToken;
import com.ngbilling.core.server.persistence.dto.user.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenDAO extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    @Modifying
    int deleteByBaseUser(UserDTO userDTO);
}
