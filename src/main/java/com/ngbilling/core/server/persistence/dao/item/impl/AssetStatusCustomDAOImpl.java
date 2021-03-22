package com.ngbilling.core.server.persistence.dao.item.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.Query;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.ngbilling.core.server.persistence.dao.AbstractJpaDAO;
import com.ngbilling.core.server.persistence.dao.item.AssetStatusCustomDAO;
import com.ngbilling.core.server.persistence.dao.item.AssetStatusDAO;
import com.ngbilling.core.server.persistence.dto.item.AssetStatusDTO;

public class AssetStatusCustomDAOImpl extends AbstractJpaDAO<AssetStatusDTO> implements AssetStatusDAO{

	
	
	@Override
	public AssetStatusDTO findAvailableStatusForItem(int id) {
		// TODO Auto-generated method stub
		
		Query query = getEntityManager().createNamedQuery("AssetStatusDTO.findAvailableStatusForItem");

		return (AssetStatusDTO) query.setParameter("item_id",  id).getSingleResult();
	}

	
	@Override
	public AssetStatusDTO findDefaultStatusForItem(int id) {
		// TODO Auto-generated method stub
		
		Query query = getEntityManager().createNamedQuery("AssetStatusDTO.findDefaultStatusForItem");

		
		return (AssetStatusDTO) query.setParameter("item_id",  id).getSingleResult();	
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


	@Override
	public List<AssetStatusDTO> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<AssetStatusDTO> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public <S extends AssetStatusDTO> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public <S extends AssetStatusDTO> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void deleteInBatch(Iterable<AssetStatusDTO> entities) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public AssetStatusDTO getOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public <S extends AssetStatusDTO> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public <S extends AssetStatusDTO> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Page<AssetStatusDTO> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public <S extends AssetStatusDTO> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Optional<AssetStatusDTO> findById(Integer id) {
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
	public void deleteAll(Iterable<? extends AssetStatusDTO> entities) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public <S extends AssetStatusDTO> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public <S extends AssetStatusDTO> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public <S extends AssetStatusDTO> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public <S extends AssetStatusDTO> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}
	}
	
	


