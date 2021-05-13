package com.ngbilling.core.server.persistence.dao.item;

import com.ngbilling.core.server.persistence.dto.item.AssetTransitionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetTransitionDAO extends JpaRepository<AssetTransitionDTO, Integer>, AssetTransitionCustomDAO {


}
