package com.ngbilling.core.server.persistence.dao.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ngbilling.core.server.persistence.dto.item.AssetDTO;

@Repository
public interface AssetDAO extends JpaRepository<AssetDTO, Integer>, AssetCustomDAO{
	
	
		

}
