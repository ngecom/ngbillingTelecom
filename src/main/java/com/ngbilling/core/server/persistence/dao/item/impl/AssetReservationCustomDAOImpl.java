package com.ngbilling.core.server.persistence.dao.item.impl;

import com.ngbilling.core.server.persistence.dao.AbstractJpaDAO;
import com.ngbilling.core.server.persistence.dao.item.AssetReservationCustomDAO;
import com.ngbilling.core.server.persistence.dao.item.AssetReservationDAO;
import com.ngbilling.core.server.persistence.dto.item.AssetReservationDTO;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class AssetReservationCustomDAOImpl extends AbstractJpaDAO<AssetReservationDTO> implements AssetReservationCustomDAO {


    @Override
    public AssetReservationDTO findActiveReservationByAsset(Integer assetId) {
        // TODO Auto-generated method stub

        Query query = getEntityManager().createNamedQuery("AssetReservationDTO.findActiveReservationByAsset");

        query.setParameter("assetId", assetId);
        return (AssetReservationDTO) query.getSingleResult();
    }



}
