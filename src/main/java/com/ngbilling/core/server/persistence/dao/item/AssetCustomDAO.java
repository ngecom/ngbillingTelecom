package com.ngbilling.core.server.persistence.dao.item;

import com.ngbilling.core.server.persistence.dto.item.AssetDTO;

import java.util.List;

public interface AssetCustomDAO {

    public int countAssetsForItem(int itemId);

    public List<AssetDTO> getAssetForItemTypeAndIdentifier(int itemTypeId, String identifier);

    public List<Integer> getAssetsForCategory(Integer categoryId);

    public List<Integer> getAssetsForItem(Integer itemId);

    public AssetDTO getForItemAndIdentifier(String identifier, int itemId);

    public AssetDTO findAssetByProductCodeAndIdentifier(String productCode, String identifier, Integer companyId);

    public List<AssetDTO> findAssetByProductCode(String productCode, Integer companyId);

    public List<AssetDTO> findAssetByProductCodeAndAssetStatusId(String productCode, Integer assetStatusId, Integer companyId);


    public List<AssetDTO> findAssetsByUser(Integer userId);

    public List<AssetDTO> getAssetsInScopeOfProduct(List<Integer> entities, boolean isGlobal);
}

