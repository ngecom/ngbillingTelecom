package com.ngbilling.core.server.persistence.dao.item.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.Query;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.ngbilling.core.server.persistence.dao.AbstractJpaDAO;
import com.ngbilling.core.server.persistence.dao.item.AssetTransitionCustomDAO;
import com.ngbilling.core.server.persistence.dao.item.AssetTransitionDAO;
import com.ngbilling.core.server.persistence.dto.item.AssetTransitionDTO;

public class AssetTransitionCustomDAOImpl extends AbstractJpaDAO<AssetTransitionDTO> implements AssetTransitionDAO{

	@Override
	public List<AssetTransitionDTO> getTransitions(int assetId) {
		// TODO Auto-generated method stub

		Query query = getEntityManager().createNamedQuery("AssetTransitionDTO.findForAsset");

		 query.setParameter("asset_id",  assetId);
		return query.getResultList();
	}

	@Override
	public List<AssetTransitionDTO> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AssetTransitionDTO> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends AssetTransitionDTO> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends AssetTransitionDTO> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteInBatch(Iterable<AssetTransitionDTO> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AssetTransitionDTO getOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends AssetTransitionDTO> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends AssetTransitionDTO> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<AssetTransitionDTO> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends AssetTransitionDTO> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<AssetTransitionDTO> findById(Integer id) {
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
	public void deleteAll(Iterable<? extends AssetTransitionDTO> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends AssetTransitionDTO> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends AssetTransitionDTO> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends AssetTransitionDTO> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends AssetTransitionDTO> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

}
