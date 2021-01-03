package com.ngbilling.core.server.persistence.dao.item;

import java.util.Date;
import java.util.List;

import com.ngbilling.core.server.persistence.dto.item.ItemTypeDTO;

public interface ItemTypeCustomDAO {
	
	
public List<Integer> findAllTypesLinkedThroughProduct(Integer typeId);


public List<ItemTypeDTO> getChildItemCategories(Integer itemTypeId);

public ItemTypeDTO getCreateInternalPlansType(Integer entityId, String description);


public ItemTypeDTO findItemTypeWithAssetManagementForItem(int itemId);

public ItemTypeDTO findByDescription(Integer entityId, String description);

public ItemTypeDTO findByGlobalDescription(Integer entityId, String description);

public List<ItemTypeDTO> findByEntityId(Integer entityId) ;


public List<ItemTypeDTO> findItemCategories(Integer entity, List<Integer> entities, boolean isRoot);

public Boolean isAssociatedToActiveOrder(Integer userId, Integer itemTypeId, Date activeSince, Date activeUntil);

public ItemTypeDTO getById(Integer itemTypeId, Integer entityId, Integer parentEntityId);
}
