package com.ngbilling.core.server.persistence.dao.item.impl;

import com.ngbilling.core.server.persistence.dao.AbstractJpaDAO;
import com.ngbilling.core.server.persistence.dao.item.AssetReservationCustomDAO;
import com.ngbilling.core.server.persistence.dto.item.AssetReservationDTO;

import javax.persistence.Query;

public class AssetReservationCustomDAOImpl extends AbstractJpaDAO<AssetReservationDTO> implements AssetReservationCustomDAO {


    @Override
    public AssetReservationDTO findActiveReservationByAsset(Integer assetId) {
        // TODO Auto-generated method stub

        Query query = getEntityManager().createNamedQuery("AssetReservationDTO.findActiveReservationByAsset");

        query.setParameter("assetId", assetId);
        return (AssetReservationDTO) query.getSingleResult();
    }



}
