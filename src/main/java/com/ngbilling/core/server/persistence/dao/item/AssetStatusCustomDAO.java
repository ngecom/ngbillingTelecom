package com.ngbilling.core.server.persistence.dao.item;

import java.util.List;

import com.ngbilling.core.server.persistence.dto.item.AssetStatusDTO;


public interface AssetStatusCustomDAO {

    public AssetStatusDTO findAvailableStatusForItem(int id);


    public AssetStatusDTO findDefaultStatusForItem(int id);


    public List<AssetStatusDTO> getStatuses(int categoryId, boolean includeInternal);


}
