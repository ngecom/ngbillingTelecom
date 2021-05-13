package com.ngbilling.core.server.persistence.dao.metafield;

import com.ngbilling.core.payload.request.metafield.DataType;
import com.ngbilling.core.payload.request.metafield.MetaFieldType;
import com.ngbilling.core.server.persistence.dto.metafield.MetaField;
import com.ngbilling.core.server.persistence.dto.metafield.MetaFieldValue;
import com.ngbilling.core.server.persistence.dto.util.EntityType;

import java.util.Date;
import java.util.List;

public interface MetaFieldCustomDAO {

    public List<MetaField> getAvailableFields(Integer entityId, EntityType[] entityType, Boolean primary);

    public MetaField getFieldByName(Integer entityId, EntityType[] entityType, String name);

    public boolean getValueByMetaFieldId(Integer metaFieldId, DataType type, MetaFieldValue value);

    public MetaField getFieldByName(Integer entityId, EntityType[] entityType, String name, Boolean primary);

    public MetaField getFieldByNameTypeAndGroup(Integer entityId, EntityType[] entityType, String name, Integer groupId);

    public MetaField getFieldByNameAndGroup(Integer entityId, String name, Integer groupId);

    public void deleteMetaFieldValuesForEntity(EntityType entityType, int metaFieldId);

    public void deleteMetaFieldValues(Integer id, EntityType entityType, List<MetaFieldValue> values);

    public Long getFieldCountByDataTypeAndName(DataType dataType, String name, Integer entityId);

    public List<Integer> findEntitiesByMetaFieldValue(MetaField metaField, String value);

    public Long countMetaFieldValuesForEntity(EntityType entityType, int metaFieldId);

    public Long getTotalFieldCount(int metaFieldId);

    public List<Integer> getCustomerFieldValues(Integer customerId, MetaFieldType type);

    public List<Integer> getCustomerFieldValues(Integer customerId, MetaFieldType type, Integer groupId, Date effectiveDate);

    public List<Integer> getAllIdsByDataTypeAndName(DataType dataType, String name);

    public MetaFieldValue getStringMetaFieldValue(Integer valueId);

    public MetaFieldValue getIntegerMetaFieldValue(Integer valueId);

    public List<Integer> getByFieldType(Integer entityId, MetaFieldType[] types);

    public List<Integer> findByValue(MetaField field, Object value, Boolean sensitive);

    public List<Integer> findByValueAndField(DataType type, Object value, Boolean sensitive, List<Integer> fields);

    public List<Integer> getValuesByCustomerAndFields(Integer customerId, List<Integer> fields);

    public List<String> getMetaFieldsByType(Integer entityId, EntityType type);

    public List<String> getMetaFieldsByCustomerType(Integer entityId, EntityType type);

}
