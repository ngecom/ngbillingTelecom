package com.ngbilling.core.server.persistence.dao.metafield.impl;

import com.ngbilling.core.payload.request.metafield.DataType;
import com.ngbilling.core.payload.request.metafield.MetaFieldType;
import com.ngbilling.core.server.persistence.dao.AbstractJpaDAO;
import com.ngbilling.core.server.persistence.dao.metafield.MetaFieldCustomDAO;
import com.ngbilling.core.server.persistence.dto.metafield.MetaField;
import com.ngbilling.core.server.persistence.dto.metafield.MetaFieldValue;
import com.ngbilling.core.server.persistence.dto.util.EntityType;

import java.util.Date;
import java.util.List;

public class MetaFieldCustomDAOImpl extends AbstractJpaDAO<MetaField> implements MetaFieldCustomDAO {


    @Override
    public List<MetaField> getAvailableFields(Integer entityId, EntityType[] entityType, Boolean primary) {
        return null;
    }

    @Override
    public MetaField getFieldByName(Integer entityId, EntityType[] entityType, String name) {
        return null;
    }

    @Override
    public boolean getValueByMetaFieldId(Integer metaFieldId, DataType type, MetaFieldValue value) {
        return false;
    }

    @Override
    public MetaField getFieldByName(Integer entityId, EntityType[] entityType, String name, Boolean primary) {
        return null;
    }

    @Override
    public MetaField getFieldByNameTypeAndGroup(Integer entityId, EntityType[] entityType, String name, Integer groupId) {
        return null;
    }

    @Override
    public MetaField getFieldByNameAndGroup(Integer entityId, String name, Integer groupId) {
        return null;
    }

    @Override
    public void deleteMetaFieldValuesForEntity(EntityType entityType, int metaFieldId) {

    }

    @Override
    public void deleteMetaFieldValues(Integer id, EntityType entityType, List<MetaFieldValue> values) {

    }

    @Override
    public Long getFieldCountByDataTypeAndName(DataType dataType, String name, Integer entityId) {
        return null;
    }

    @Override
    public List<Integer> findEntitiesByMetaFieldValue(MetaField metaField, String value) {
        return null;
    }

    @Override
    public Long countMetaFieldValuesForEntity(EntityType entityType, int metaFieldId) {
        return null;
    }

    @Override
    public Long getTotalFieldCount(int metaFieldId) {
        return null;
    }

    @Override
    public List<Integer> getCustomerFieldValues(Integer customerId, MetaFieldType type) {
        return null;
    }

    @Override
    public List<Integer> getCustomerFieldValues(Integer customerId, MetaFieldType type, Integer groupId, Date effectiveDate) {
        return null;
    }

    @Override
    public List<Integer> getAllIdsByDataTypeAndName(DataType dataType, String name) {
        return null;
    }

    @Override
    public MetaFieldValue getStringMetaFieldValue(Integer valueId) {
        return null;
    }

    @Override
    public MetaFieldValue getIntegerMetaFieldValue(Integer valueId) {
        return null;
    }

    @Override
    public List<Integer> getByFieldType(Integer entityId, MetaFieldType[] types) {
        return null;
    }

    @Override
    public List<Integer> findByValue(MetaField field, Object value, Boolean sensitive) {
        return null;
    }

    @Override
    public List<Integer> findByValueAndField(DataType type, Object value, Boolean sensitive, List<Integer> fields) {
        return null;
    }

    @Override
    public List<Integer> getValuesByCustomerAndFields(Integer customerId, List<Integer> fields) {
        return null;
    }

    @Override
    public List<String> getMetaFieldsByType(Integer entityId, EntityType type) {
        return null;
    }

    @Override
    public List<String> getMetaFieldsByCustomerType(Integer entityId, EntityType type) {
        return null;
    }
}
