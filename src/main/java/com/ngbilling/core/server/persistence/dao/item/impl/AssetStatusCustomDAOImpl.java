package com.ngbilling.core.server.persistence.dao.item.impl;

import java.util.List;

import javax.persistence.Query;

import com.ngbilling.core.server.persistence.dao.AbstractJpaDAO;
import com.ngbilling.core.server.persistence.dao.item.AssetStatusCustomDAO;
import com.ngbilling.core.server.persistence.dto.item.AssetStatusDTO;

public class AssetStatusCustomDAOImpl extends AbstractJpaDAO<AssetStatusDTO> implements AssetStatusCustomDAO {


    @Override
    public AssetStatusDTO findAvailableStatusForItem(int id) {
        // TODO Auto-generated method stub

        Query query = getEntityManager().createNamedQuery("AssetStatusDTO.findAvailableStatusForItem");

        return (AssetStatusDTO) query.setParameter("item_id", id).getSingleResult();
    }


    @Override
    public AssetStatusDTO findDefaultStatusForItem(int id) {
        // TODO Auto-generated method stub

        Query query = getEntityManager().createNamedQuery("AssetStatusDTO.findDefaultStatusForItem");


        return (AssetStatusDTO) query.setParameter("item_id", id).getSingleResult();
    }


    @Override
    public List<AssetStatusDTO> getStatuses(int categoryId, boolean includeInternal) {
        // TODO Auto-generated method stub

        Query queryInternal = getEntityManager().createNamedQuery("AssetStatusDTO.findForItemType");

        Query queryNotInternal = getEntityManager().createNamedQuery("AssetStatusDTO.findForItemTypeNotInternal");


        List<AssetStatusDTO> assetStatusDTOList = (includeInternal) ? queryInternal.setParameter("item_type_id", categoryId).getResultList() :
                queryNotInternal.setParameter("item_type_id", categoryId).getResultList();

        return assetStatusDTOList;
    }



}
	
	


