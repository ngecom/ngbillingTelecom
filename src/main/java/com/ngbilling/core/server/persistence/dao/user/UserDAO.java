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
    public CompanyDTO getEntity(Integer userId);

    public Optional<UserDTO> findByUserName(String username);

    @Query(value = "select count(*) from(  " +
            "select count(*) from meta_field_value mfv  where meta_field_name_id=(select id from meta_field_name mfn2 where name='contact.email') " +
            "and string_value=:email " +
            "union " +
            "select count(*) from contact c where email =:email " +
            " )d ", nativeQuery = true)
    public Optional<Integer> findEmail(String email);

    public Boolean existsByUserName(String username);

    @Query(value = "SELECT jb_allow_signup FROM jb_host_master", nativeQuery = true)
    public Boolean isAllowSignup();

}
