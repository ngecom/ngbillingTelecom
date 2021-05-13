package com.ngbilling.core.server.persistence.dao.item;

import com.ngbilling.core.server.persistence.dto.item.AssetDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetDAO extends JpaRepository<AssetDTO, Integer>, AssetCustomDAO {


}
