package com.ngbilling.core.server.persistence.dao.metafield;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ngbilling.core.server.persistence.dto.metafield.MetaFieldGroup;
@Repository
public interface MetaFieldGroupDAO extends JpaRepository<MetaFieldGroup, Integer>,MetaFieldGroupCustomDAO{

		
}
