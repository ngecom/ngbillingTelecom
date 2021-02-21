package com.ngbilling.core.server.persistence.dao.metafield.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.ngbilling.core.server.persistence.dao.AbstractJpaDAO;
import com.ngbilling.core.server.persistence.dao.metafield.MetaFieldGroupDAO;
import com.ngbilling.core.server.persistence.dto.metafield.MetaFieldGroup;
import com.ngbilling.core.server.persistence.dto.util.EntityType;

public class MetaFieldGroupCustomDAOImpl extends AbstractJpaDAO<MetaFieldGroup> implements MetaFieldGroupDAO{

	@Override
	public List<MetaFieldGroup> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MetaFieldGroup> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends MetaFieldGroup> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends MetaFieldGroup> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteInBatch(Iterable<MetaFieldGroup> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MetaFieldGroup getOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends MetaFieldGroup> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends MetaFieldGroup> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<MetaFieldGroup> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends MetaFieldGroup> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<MetaFieldGroup> findById(Integer id) {
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
	public void deleteAll(Iterable<? extends MetaFieldGroup> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends MetaFieldGroup> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends MetaFieldGroup> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends MetaFieldGroup> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends MetaFieldGroup> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<MetaFieldGroup> getAvailableFieldGroups(Integer entityId, EntityType entityType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MetaFieldGroup getGroupByName(Integer entityId, EntityType entityType, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
