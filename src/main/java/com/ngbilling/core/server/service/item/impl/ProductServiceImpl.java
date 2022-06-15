package com.ngbilling.core.server.service.item.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ngbilling.core.server.persistence.dao.item.ItemTypeDAO;
import com.ngbilling.core.server.persistence.dto.item.ItemTypeDTO;
import com.ngbilling.core.server.persistence.dto.user.CompanyDTO;
import com.ngbilling.core.server.service.item.ProductService;
import com.ngbilling.core.server.util.ServerConstants;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ItemTypeDAO itemTypeDAO;

    @Override
    @Transactional
    public void createInternalTypeCategory(CompanyDTO companyDTO) {
        // TODO Auto-generated method stub
        ItemTypeDTO itemTypeDTO = new ItemTypeDTO();
        itemTypeDTO.setEntity(companyDTO);
        itemTypeDTO.setAllowAssetManagement(0);
        itemTypeDTO.setDescription(ServerConstants.PLANS_INTERNAL_CATEGORY_NAME);
        itemTypeDTO.setInternal(true);
        itemTypeDTO.setOrderLineTypeId(ServerConstants.ORDER_LINE_TYPE_ITEM);
        Set<CompanyDTO> entities = new HashSet<CompanyDTO>();
        entities.add(companyDTO);
        itemTypeDTO.setEntities(entities);
        itemTypeDAO.save(itemTypeDTO);


    }


}
