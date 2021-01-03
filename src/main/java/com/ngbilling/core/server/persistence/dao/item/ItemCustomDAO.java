package com.ngbilling.core.server.persistence.dao.item;

import java.util.List;

import com.ngbilling.core.server.persistence.dto.item.ItemDTO;

public interface ItemCustomDAO {
	
	public Long findProductCountByCurrencyAndEntity(Integer currencyId, Integer entityId );
	
	public boolean isSubscribedByItem(Integer userId, Integer itemId) ;
	
	public List<ItemDTO> findItemsByInternalNumber(String internalNumber);
	
	public List<ItemDTO> findAllByItemType(Integer itemTypeId) ;
	
	public List<ItemDTO> findItemsByCategoryPrefix(String prefix);
	
	
	
	public Long findProductCountByInternalNumber(String internalNumber, Integer entityId, boolean isNew, Integer id);
	
	public List<ItemDTO> findByEntityId(Integer entityId) ;
	
	public List<ItemDTO> findItems(Integer entity, List<Integer> entities, boolean isRoot);
	
	public boolean isProductVisibleToCompany(Integer itemId, Integer entityId, Integer parentId);
	

}
