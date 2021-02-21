package com.ngbilling.core.server.persistence.dao.metafield.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.ngbilling.core.payload.request.metafield.DataType;
import com.ngbilling.core.payload.request.metafield.MetaFieldType;
import com.ngbilling.core.server.persistence.dao.AbstractJpaDAO;
import com.ngbilling.core.server.persistence.dao.metafield.MetaFieldDAO;
import com.ngbilling.core.server.persistence.dto.metafield.MetaField;
import com.ngbilling.core.server.persistence.dto.metafield.MetaFieldValue;
import com.ngbilling.core.server.persistence.dto.util.EntityType;

public class MetaFieldCustomDAOImpl extends AbstractJpaDAO<MetaField> implements MetaFieldDAO{

	@Override
	public List<MetaField> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MetaField> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends MetaField> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends MetaField> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteInBatch(Iterable<MetaField> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MetaField getOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends MetaField> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends MetaField> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<MetaField> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends MetaField> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<MetaField> findById(Integer id) {
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
	public void delete(MetaField entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends MetaField> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends MetaField> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends MetaField> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends MetaField> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends MetaField> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<MetaField> getAvailableFields(Integer entityId, EntityType[] entityType, Boolean primary) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MetaField getFieldByName(Integer entityId, EntityType[] entityType, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getValueByMetaFieldId(Integer metaFieldId, DataType type, MetaFieldValue value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public MetaField getFieldByName(Integer entityId, EntityType[] entityType, String name, Boolean primary) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MetaField getFieldByNameTypeAndGroup(Integer entityId, EntityType[] entityType, String name,
			Integer groupId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MetaField getFieldByNameAndGroup(Integer entityId, String name, Integer groupId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteMetaFieldValuesForEntity(EntityType entityType, int metaFieldId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMetaFieldValues(Integer id, EntityType entityType, List<MetaFieldValue> values) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Long getFieldCountByDataTypeAndName(DataType dataType, String name, Integer entityId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> findEntitiesByMetaFieldValue(MetaField metaField, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long countMetaFieldValuesForEntity(EntityType entityType, int metaFieldId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getTotalFieldCount(int metaFieldId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> getCustomerFieldValues(Integer customerId, MetaFieldType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> getCustomerFieldValues(Integer customerId, MetaFieldType type, Integer groupId,
			Date effectiveDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> getAllIdsByDataTypeAndName(DataType dataType, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MetaFieldValue getStringMetaFieldValue(Integer valueId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MetaFieldValue getIntegerMetaFieldValue(Integer valueId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> getByFieldType(Integer entityId, MetaFieldType[] types) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> findByValue(MetaField field, Object value, Boolean sensitive) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> findByValueAndField(DataType type, Object value, Boolean sensitive, List<Integer> fields) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> getValuesByCustomerAndFields(Integer customerId, List<Integer> fields) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getMetaFieldsByType(Integer entityId, EntityType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getMetaFieldsByCustomerType(Integer entityId, EntityType type) {
		// TODO Auto-generated method stub
		return null;
	}

}
