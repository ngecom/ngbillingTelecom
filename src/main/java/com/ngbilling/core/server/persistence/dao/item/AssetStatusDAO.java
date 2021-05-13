package com.ngbilling.core.server.persistence.dao.item;

import com.ngbilling.core.server.persistence.dto.item.AssetStatusDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetStatusDAO extends JpaRepository<AssetStatusDTO, Integer>, AssetStatusCustomDAO {


}
