package com.ngbilling.core.server.persistence.dao.item.impl;

import java.util.List;

import javax.persistence.Query;

import com.ngbilling.core.server.persistence.dao.AbstractJpaDAO;
import com.ngbilling.core.server.persistence.dao.item.AssetTransitionCustomDAO;
import com.ngbilling.core.server.persistence.dto.item.AssetTransitionDTO;

public class AssetTransitionCustomDAOImpl extends AbstractJpaDAO<AssetTransitionDTO> implements AssetTransitionCustomDAO {

    @Override
    public List<AssetTransitionDTO> getTransitions(int assetId) {
        // TODO Auto-generated method stub

        Query query = getEntityManager().createNamedQuery("AssetTransitionDTO.findForAsset");

        query.setParameter("asset_id", assetId);
        return query.getResultList();
    }


}
