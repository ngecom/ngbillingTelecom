package com.ngbilling.core.server.service.user.impl;

import com.ngbilling.core.server.persistence.dao.item.ItemTypeDAO;
import com.ngbilling.core.server.persistence.dto.item.ItemTypeDTO;
import com.ngbilling.core.server.persistence.dto.user.CompanyDTO;
import com.ngbilling.core.server.service.user.ProductService;
import com.ngbilling.core.server.util.ServerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ItemTypeDAO itemTypeDAO;

    @Override
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
