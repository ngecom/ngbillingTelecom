package com.ngbilling.core.server.service.metafield;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ngbilling.core.payload.request.metafield.DataType;
import com.ngbilling.core.payload.request.metafield.MetaFieldType;
import com.ngbilling.core.payload.request.metafield.MetaFieldValueWS;
import com.ngbilling.core.payload.request.metafield.MetaFieldWS;
import com.ngbilling.core.payload.request.metafield.ValidationRuleWS;
import com.ngbilling.core.server.persistence.dto.metafield.MetaField;
import com.ngbilling.core.server.persistence.dto.metafield.MetaFieldValue;
import com.ngbilling.core.server.persistence.dto.metafield.ValidationRule;
import com.ngbilling.core.server.persistence.dto.util.EntityType;
import com.ngbilling.core.server.util.metafield.MetaContent;

public interface MetaFieldService {
	public  MetaField getFieldByName(Integer entityId, EntityType[] entityType, String name);
	public  Map<String, MetaField> getAvailableFields(Integer entityId, EntityType[] entityType);
	public  List<MetaField> getAvailableFieldsList(Integer entityId, EntityType[] entityType);
	public  List<MetaField> getAllAvailableFieldsList(Integer entityId, EntityType[] entityType);
	public  Map<Integer, List<MetaField>> getAvailableAccountTypeFieldsMap(Integer accountTypeId);
	public  Set<MetaField> getPaymentMethodMetaFields(Integer paymentMetohdTypeId);
	public  void validateMetaFields(Integer entityId, EntityType type, MetaFieldValueWS[] metaFields) ;
	public  List<String> getMetaFieldsByType(Integer entityId, EntityType type) ;
	public  List<String> getMetaFieldsByCustomerType(Integer entityId, EntityType type) ;
	public  void validateMetaFields(Integer entityId, EntityType[] type, MetaFieldValueWS[] metaFields) ;
	public  void validateMetaFields(Collection<MetaField> metaFieldsCollection, MetaFieldValueWS[] metaFields) ;
	public  void validateMetaFields( Collection<MetaField> availableMetaFields, MetaContent customizedEntity) ;
	public  void validateMetaField(MetaField field, MetaFieldValue value, MetaContent source) ;
	public  void validateUniqueMF(MetaFieldValue value) ;
	public  MetaFieldValueWS[] convertMetaFieldsToWS(MetaContent entity);
	public  Set<MetaField> convertMetaFieldsToDTO(Collection<MetaFieldWS> metaFields, Integer entityId) ;
	public  boolean isMetaFieldUsed(EntityType entityType, Integer metaFieldId);
	public  String getStringMetaFieldValue(Integer customerId, MetaFieldType type, Integer group, Date effectiveDate);
	public  Integer getIntegerMetaFieldValue(Integer customerId, MetaFieldType type, Integer group, Date effectiveDate);
	public MetaField create(MetaField dto) ;
	public void update(MetaField dto) ;
	public void deleteIfNotParticipant(int metaFieldId) ;
	public void delete(int metaFieldId) ;
	public  MetaField getMetaField(Integer metafieldId);
	 public  void validateAttributes(Collection<ValidationRule> models) ;
	 public  void validateMetaFieldsChanges(Collection<MetaField> newMetaFields, Collection<MetaField> currentMetaFields) ;
	 public  Collection<Integer> updateMetaFieldsCollection(Collection<MetaField> newMetaFields, Collection<MetaField> currentMetaFields)  ;
	 public  List<MetaField> getMetaFields(Collection<Integer> entityIds, EntityType type);
	 public  MetaField getDTO(MetaFieldWS ws,Integer entityId);
	 public  MetaFieldValueWS getWS(MetaFieldValue metaFieldValue, Integer groupId);
	 public MetaFieldWS getWS(MetaField dto) ;
	 public  MetaFieldValueWS getWS(@SuppressWarnings("rawtypes") MetaFieldValue metaFieldValue);
	 public  ValidationRuleWS getValidationRuleWS(ValidationRule dto) ;
	 public ValidationRule getValidationRuleDTO(ValidationRuleWS ws);
	 public MetaFieldValue createValueFromDataType(MetaField metaField, Object value,DataType dataType);
	 
	 
	 
	 
	 
	 
}
