package com.ngbilling.core.server.persistence.dao.item.impl;

import com.ngbilling.core.server.persistence.dao.AbstractJpaDAO;
import com.ngbilling.core.server.persistence.dao.item.AssetTransitionCustomDAO;
import com.ngbilling.core.server.persistence.dao.item.AssetTransitionDAO;
import com.ngbilling.core.server.persistence.dto.item.AssetTransitionDTO;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class AssetTransitionCustomDAOImpl extends AbstractJpaDAO<AssetTransitionDTO> implements AssetTransitionCustomDAO {

    @Override
    public List<AssetTransitionDTO> getTransitions(int assetId) {
        // TODO Auto-generated method stub

        Query query = getEntityManager().createNamedQuery("AssetTransitionDTO.findForAsset");

        query.setParameter("asset_id", assetId);
        return query.getResultList();
    }


}
