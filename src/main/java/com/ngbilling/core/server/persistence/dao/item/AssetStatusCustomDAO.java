package com.ngbilling.core.server.persistence.dao.item;

import com.ngbilling.core.server.persistence.dto.item.AssetStatusDTO;

import java.util.List;


public interface AssetStatusCustomDAO {

    public AssetStatusDTO findAvailableStatusForItem(int id);


    public AssetStatusDTO findDefaultStatusForItem(int id);


    public List<AssetStatusDTO> getStatuses(int categoryId, boolean includeInternal);


}
