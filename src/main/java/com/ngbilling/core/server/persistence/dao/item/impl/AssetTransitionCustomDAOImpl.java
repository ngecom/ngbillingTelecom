package com.ngbilling.core.server.persistence.dao.item.impl;

import com.ngbilling.core.server.persistence.dao.AbstractJpaDAO;
import com.ngbilling.core.server.persistence.dao.item.AssetTransitionCustomDAO;
import com.ngbilling.core.server.persistence.dto.item.AssetTransitionDTO;

import javax.persistence.Query;
import java.util.List;

public class AssetTransitionCustomDAOImpl extends AbstractJpaDAO<AssetTransitionDTO> implements AssetTransitionCustomDAO {

    @Override
    public List<AssetTransitionDTO> getTransitions(int assetId) {
        // TODO Auto-generated method stub

        Query query = getEntityManager().createNamedQuery("AssetTransitionDTO.findForAsset");

        query.setParameter("asset_id", assetId);
        return query.getResultList();
    }


}
