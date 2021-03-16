package com.ngbilling.core.server.persistence.dao.item.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.ngbilling.core.server.persistence.dao.AbstractJpaDAO;
import com.ngbilling.core.server.persistence.dao.item.AssetCustomDAO;
import com.ngbilling.core.server.persistence.dao.item.AssetDAO;
import com.ngbilling.core.server.persistence.dto.item.AssetDTO;

public class AssetCustomDAOImpl extends AbstractJpaDAO<AssetDTO> implements AssetDAO{

	@Override
	public int countAssetsForItem(int itemId) {
		// TODO Auto-generated method stub
		Query query = getEntityManager().createNamedQuery("AssetDTO.countForItem");

		
		 query.setParameter("item_id",  itemId);
		
		return (int) query.getSingleResult();
	}

	
	@Override
	public List<AssetDTO> getAssetForItemTypeAndIdentifier(int itemTypeId, String identifier) {
		// TODO Auto-generated method stub
		Query query = getEntityManager().createNamedQuery("AssetDTO.identifierForIdentifierAndCategory");

		
		 query.setParameter("item_type_id",  itemTypeId);
		 query.setParameter("identifier",  identifier);

		return query.getResultList();
	}

	
	@Override
	public List<Integer> getAssetsForCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Query query = getEntityManager().createNamedQuery("AssetDTO.idsForItemType");

		
		 query.setParameter("item_type_id",  categoryId);
		
		

		return query.getResultList();
	}

	
	@Override
	public List<Integer> getAssetsForItem(Integer itemId) {
		// TODO Auto-generated method stub
		Query query = getEntityManager().createNamedQuery("AssetDTO.idsForItem");

		
		 query.setParameter("item_id",  itemId);
		
		
		return query.getResultList();
	}

	
	@Override
	public AssetDTO getForItemAndIdentifier(String identifier, int itemId) {
		// TODO Auto-generated method stub
		Query query = getEntityManager().createNamedQuery("AssetDTO.getForItemAndIdentifier");

		
		 query.setParameter("itemId",  itemId);
		 query.setParameter("identifier",  identifier);

		
		return (AssetDTO) query.getSingleResult();
	}

	private static final String FIND_ASSET_BY_PRODUCT_CODE_AND_IDENTIFIER = "from AssetDTO a "
            + " where a.item.internalNumber= :productCode "
            + " and a.identifier = :identifier and a.entity.id= :companyId and a.deleted=0";

	@Override
	public AssetDTO findAssetByProductCodeAndIdentifier(String productCode, String identifier, Integer companyId) {
		// TODO Auto-generated method stub
		Query query = getEntityManager().createQuery(FIND_ASSET_BY_PRODUCT_CODE_AND_IDENTIFIER);
		query.setParameter("productCode", productCode);
		query.setParameter("identifier", identifier);
		query.setParameter("companyId", companyId);
		return (AssetDTO) query.getSingleResult();
	}

	private static final String FIND_ASSET_BY_PRODUCT_CODE = "select u from AssetDTO u where u.item.internalNumber = ?1 and u.entity.id = ?2 and u.deleted = 0";
	@Override
	public List<AssetDTO> findAssetByProductCode(String productCode, Integer companyId) {
		// TODO Auto-generated method stub
		TypedQuery<AssetDTO> query = getEntityManager().createQuery(FIND_ASSET_BY_PRODUCT_CODE,AssetDTO.class);
		query.setParameter(1, productCode);
		query.setParameter(2, companyId);
		
		return query.getResultList();
	}

	private static final String FIND_ASSET_BY_PRODUCT_CODE_AND_STATUS = "select u from AssetDTO u where u.item.internalNumber = ?1 and u.entity.id = ?2 and u.assetStatus = ?3 and u.deleted = 0";

	@Override
	public List<AssetDTO> findAssetByProductCodeAndAssetStatusId(String productCode, Integer assetStatusId,
			Integer companyId) {
		// TODO Auto-generated method stub
		TypedQuery<AssetDTO> query = getEntityManager().createQuery(FIND_ASSET_BY_PRODUCT_CODE_AND_STATUS,AssetDTO.class);
		query.setParameter(1, productCode);
		query.setParameter(2, companyId);
		query.setParameter(3, assetStatusId);
		return query.getResultList();
	}

	private static final String FIND_ASSET_BY_USER = "select u from AssetDTO u where u.deleted = 0 and u.orderLine.purchaseOrder.baseUserByUserId.id = ?1"
			+ "order by id desc";

	@Override
	public List<AssetDTO> findAssetsByUser(Integer userId) {
		// TODO Auto-generated method stub
		//Criteria c = Criteria
		TypedQuery<AssetDTO> query = getEntityManager().createQuery(FIND_ASSET_BY_USER,AssetDTO.class);
		query.setParameter(1, userId);
		return query.getResultList();		
	}

	private static final String GET_ASSETS_IN_SCOPE_OF_PRODUCT = "select u from AssetDTO u join fetch u.item it where it.global = :global or "
			+ "it.entity.id IN (:id) or it.entities.id IN (:id)";

	@Override
	public List<AssetDTO> getAssetsInScopeOfProduct(List<Integer> entities, boolean isGlobal) {
		// TODO Auto-generated method stub
		Query query = getEntityManager().createNamedQuery(GET_ASSETS_IN_SCOPE_OF_PRODUCT);
		
		 query.setParameter("id",  entities);
		 query.setParameter("global",  isGlobal);
		return query.getResultList();
	}


	@Override
	public List<AssetDTO> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<AssetDTO> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public <S extends AssetDTO> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public <S extends AssetDTO> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void deleteInBatch(Iterable<AssetDTO> entities) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public AssetDTO getOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public <S extends AssetDTO> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public <S extends AssetDTO> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Page<AssetDTO> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public <S extends AssetDTO> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Optional<AssetDTO> findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deleteAll(Iterable<? extends AssetDTO> entities) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public <S extends AssetDTO> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public <S extends AssetDTO> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public <S extends AssetDTO> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public <S extends AssetDTO> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

}
