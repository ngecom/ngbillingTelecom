package com.ngbilling.core.server.persistence.dao.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ngbilling.core.server.persistence.dto.item.AssetReservationDTO;

@Repository
public interface AssetReservationDAO extends JpaRepository<AssetReservationDTO, Integer>, AssetReservationCustomDAO {

}
