package com.ngbilling.core.server.persistence.dao.item;

import com.ngbilling.core.server.persistence.dto.item.AssetTransitionDTO;

import java.util.List;

public interface AssetTransitionCustomDAO {

    public List<AssetTransitionDTO> getTransitions(int assetId);

}
