package com.ngbilling.core.server.persistence.dao.item;

import java.util.List;

import com.ngbilling.core.server.persistence.dto.item.AssetTransitionDTO;

public interface AssetTransitionCustomDAO {

	public List<AssetTransitionDTO> getTransitions(int assetId);

}
