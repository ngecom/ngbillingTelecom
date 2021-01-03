package com.ngbilling.core.server.persistence.dao.item;

import com.ngbilling.core.server.persistence.dto.item.AssetReservationDTO;


public interface AssetReservationCustomDAO {

	public AssetReservationDTO findActiveReservationByAsset(Integer assetId);



}
