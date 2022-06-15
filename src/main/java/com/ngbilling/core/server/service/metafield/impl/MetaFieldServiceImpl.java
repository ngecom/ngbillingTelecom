package com.ngbilling.core.server.service.metafield.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ngbilling.core.common.exception.MetaFieldException;
import com.ngbilling.core.payload.request.metafield.BooleanMetaFieldValue;
import com.ngbilling.core.payload.request.metafield.DataType;
import com.ngbilling.core.payload.request.metafield.DateMetaFieldValue;
import com.ngbilling.core.payload.request.metafield.DecimalMetaFieldValue;
import com.ngbilling.core.payload.request.metafield.IntegerMetaFieldValue;
import com.ngbilling.core.payload.request.metafield.JsonMetaFieldValue;
import com.ngbilling.core.payload.request.metafield.ListMetaFieldValue;
import com.ngbilling.core.payload.request.metafield.MetaFieldType;
import com.ngbilling.core.payload.request.metafield.MetaFieldValueWS;
import com.ngbilling.core.payload.request.metafield.MetaFieldWS;
import com.ngbilling.core.payload.request.metafield.StringMetaFieldValue;
import com.ngbilling.core.payload.request.metafield.ValidationRuleWS;
import com.ngbilling.core.payload.request.util.InternationalDescriptionWS;
import com.ngbilling.core.server.persistence.dao.metafield.MetaFieldDAO;
import com.ngbilling.core.server.persistence.dao.metafield.MetaFieldValueDAO;
import com.ngbilling.core.server.persistence.dao.metafield.ValidationRuleDAO;
import com.ngbilling.core.server.persistence.dao.util.LanguageDAO;
import com.ngbilling.core.server.persistence.dto.metafield.MetaField;
import com.ngbilling.core.server.persistence.dto.metafield.MetaFieldValue;
import com.ngbilling.core.server.persistence.dto.metafield.ValidationRule;
import com.ngbilling.core.server.persistence.dto.user.CompanyDTO;
import com.ngbilling.core.server.persistence.dto.util.EntityType;
import com.ngbilling.core.server.persistence.dto.util.LanguageDTO;
import com.ngbilling.core.server.service.metafield.MetaFieldService;
import com.ngbilling.core.server.util.metafield.MetaContent;
import com.ngbilling.core.server.util.pricing.AttributeUtils;
import com.ngbilling.core.server.validator.metafield.ValidationRuleType;

@Service
public class MetaFieldServiceImpl implements MetaFieldService {

    @Autowired
    private MetaFieldDAO metaFieldDAO;

    @Autowired
    private LanguageDAO languageDAO;

    @Autowired
    private MetaFieldValueDAO metaFieldValueDAO;

    @Autowired
    private ValidationRuleDAO validationRuleDAO;

    private static String getErrorsAsString(String[] errors) {
        StringBuilder builder = new StringBuilder();
        if (errors != null) {
            builder.append(". Errors: ");
            for (String error : errors) {
                builder.append(error);
                builder.append(System.getProperty(","));
            }
        }
        return builder.toString();
    }

    private static void mergeBasicProperties(MetaField destination, MetaField source) {
        destination.setName(source.getName());
        destination.setPrimary(source.getPrimary());
        destination.setValidationRule(source.getValidationRule());
        destination.setDataType(source.getDataType());
        destination.setDefaultValue(source.getDefaultValue());
        destination.setDisabled(source.isDisabled());
        destination.setMandatory(source.isMandatory());
        destination.setDisplayOrder(source.getDisplayOrder());
        destination.setFieldUsage(source.getFieldUsage());
    }

    @Override
    public MetaField getFieldByName(Integer entityId, EntityType[] entityType, String name) {
        // TODO Auto-generated method stub
        return metaFieldDAO.getFieldByName(entityId, entityType, name);
    }

    @Override
    public Map<String, MetaField> getAvailableFields(Integer entityId, EntityType[] entityType) {
        // TODO Auto-generated method stub
        List<MetaField> entityFields = metaFieldDAO.getAvailableFields(entityId, entityType, true);
        Map<String, MetaField> result = new LinkedHashMap<String, MetaField>();
        if (entityFields != null) {
            for (MetaField field : entityFields) {
                result.put(field.getName(), field);
            }
        }
        return result;
    }

    @Override
    public List<MetaField> getAvailableFieldsList(Integer entityId, EntityType[] entityType) {
        // TODO Auto-generated method stub
        return metaFieldDAO.getAvailableFields(entityId, entityType, true);
    }

    @Override
    public List<MetaField> getAllAvailableFieldsList(Integer entityId, EntityType[] entityType) {
        // TODO Auto-generated method stub
        return metaFieldDAO.getAvailableFields(entityId, entityType, true);
    }

    @Override
    public Map<Integer, List<MetaField>> getAvailableAccountTypeFieldsMap(Integer accountTypeId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<MetaField> getPaymentMethodMetaFields(Integer paymentMetohdTypeId) {
        // TODO Auto-generated method stub

        return null;
    }

    @Override
    public void validateMetaFields(Integer entityId, EntityType type, MetaFieldValueWS[] metaFields) {
        // TODO Auto-generated method stub
        validateMetaFields(entityId, new EntityType[]{type}, metaFields);

    }

    @Override
    public List<String> getMetaFieldsByType(Integer entityId, EntityType type) {
        // TODO Auto-generated method stub
        return metaFieldDAO.getMetaFieldsByType(entityId, type);
    }

    @Override
    public List<String> getMetaFieldsByCustomerType(Integer entityId, EntityType type) {
        // TODO Auto-generated method stub
        return metaFieldDAO.getMetaFieldsByCustomerType(entityId, type);
    }

    @Override
    public void validateMetaFields(Integer entityId, EntityType[] type, MetaFieldValueWS[] metaFields) {
        // TODO Auto-generated method stub
        Collection<MetaField> metaFieldsCollection = metaFieldDAO.getAvailableFields(entityId, type, true);
        validateMetaFields(metaFieldsCollection, metaFields);

    }

    @Override
    public void validateMetaFields(Collection<MetaField> metaFieldsCollection, MetaFieldValueWS[] metaFields) {
        // TODO Auto-generated method stub
        for (MetaField field : metaFieldsCollection) {
            MetaFieldValue value = field.createValue();
            if (metaFields != null) {
                for (MetaFieldValueWS valueWS : metaFields) {
                    if (field.getName().equals(valueWS.getFieldName())) {
                        value.setValue(valueWS.getValue());
                        break;
                    }
                }
            }
            // TODO (pai) validate metafields with source context !
            validateMetaField(field, value, null);
        }

    }

    @Override
    public void validateMetaFields(Collection<MetaField> availableMetaFields, MetaContent customizedEntity) {
        // TODO Auto-generated method stub
        for (MetaField field : availableMetaFields) {
            MetaFieldValue value = customizedEntity.getMetaField(field.getName());
            validateMetaField(field, value, customizedEntity);
        }
    }

    @Override
    public void validateMetaField(MetaField field, MetaFieldValue value, MetaContent source) {
        // TODO Auto-generated method stub
        if (field.isDisabled())
            return;

        if (field.isMandatory() && value == null) {
            String[] error = new String[]{"MetaFieldValue,value,value.cannot.be.null," + field.getName()};
            throw new MetaFieldException("Field value failed validation.", error);
        }

        if (field.isMandatory() && value != null && value.getValue() != null && "".equals(value.getValue().toString().trim())) {
            String[] error = new String[]{"MetaFieldValue,value,metafield.validation.value.unspecified," + field.getName()};
            throw new MetaFieldException("Metafield value must be specified",
                    new String[]{"MetaFieldValue,value,metafield.validation.value.unspecified," + field.getName()});
        }

        if (field.isUnique() && field.getEntityType() != EntityType.CUSTOMER) {
            validateUniqueMF(value);
        }

        if (value != null) {
            value.validate(field.getEntity().getLanguageId(), source);
        }
    }

    @Override
    public void validateUniqueMF(MetaFieldValue value) {
        // TODO Auto-generated method stub

        if (null == value.getValue() || !value.getField().isUnique()) return;
        MetaField field = value.getField();

        boolean uniqueMetaField = metaFieldDAO.getValueByMetaFieldId(field.getId(), field.getDataType(), value);
        if (!uniqueMetaField) {
            String[] error = new String[]{"MetaFieldValue,value,metafield.validation.value.unique," + field.getName()};
            throw new MetaFieldException("Metafield value must be unique",
                    new String[]{"MetaFieldValue,value,metafield.validation.value.unique," + field.getName()});

        }
    }

    @Override
    public MetaFieldValueWS[] convertMetaFieldsToWS(MetaContent entity) {
        // TODO Auto-generated method stub
        List<MetaFieldValue> metaFieldValues = entity.getMetaFields();
        MetaFieldValueWS[] result = new MetaFieldValueWS[metaFieldValues.size()];

        int idx = 0;
        for (MetaFieldValue metaFieldValue : metaFieldValues) {
            MetaFieldValueWS metaFieldValueWS = getWS(metaFieldValue);
            result[idx++] = metaFieldValueWS;
        }

        return result;

    }

    @Override
    public Set<MetaField> convertMetaFieldsToDTO(Collection<MetaFieldWS> metaFields, Integer entityId) {
        // TODO Auto-generated method stub
        Set<MetaField> dtoList = new HashSet<MetaField>(metaFields.size() * 2);

        if (metaFields != null) {
            for (MetaFieldWS metaField : metaFields) {
                MetaField mf = getDTO(metaField, entityId);
                dtoList.add(mf);
            }
        }
        return dtoList;
    }

    @Override
    public boolean isMetaFieldUsed(EntityType entityType, Integer metaFieldId) {
        // TODO Auto-generated method stub
        return metaFieldDAO.countMetaFieldValuesForEntity(entityType, metaFieldId) != 0;
    }

    @Override
    public String getStringMetaFieldValue(Integer customerId, MetaFieldType type, Integer group, Date effectiveDate) {
        // TODO Auto-generated method stub
        List<Integer> values = metaFieldDAO.getCustomerFieldValues(customerId, type, group, effectiveDate);
        Integer valueId = null != values && values.size() > 0 ? values.get(0) : null;
        MetaFieldValue valueField = null != valueId ? metaFieldDAO.getStringMetaFieldValue(valueId) : null;
        return null != valueField ? (String) valueField.getValue() : null;
    }

    @Override
    public Integer getIntegerMetaFieldValue(Integer customerId, MetaFieldType type, Integer group, Date effectiveDate) {
        // TODO Auto-generated method stub
        List<Integer> values = metaFieldDAO.getCustomerFieldValues(customerId, type, group, effectiveDate);
        Integer valueId = null != values && values.size() > 0 ? values.get(0) : null;
        MetaFieldValue valueField = null != valueId ? metaFieldDAO.getIntegerMetaFieldValue(valueId) : null;
        return null != valueField ? (Integer) valueField.getValue() : null;
    }

    @Override
    public MetaField create(MetaField dto) {
        // TODO Auto-generated method stub
        MetaField metaField = new MetaField();
        metaField.setEntity(dto.getEntity());
        metaField.setEntityType(dto.getEntityType());
        metaField.setDataType(dto.getDataType());
        metaField.setName(dto.getName());
        metaField.setDisplayOrder(dto.getDisplayOrder());
        metaField.setMandatory(dto.isMandatory());
        metaField.setDisabled(dto.isDisabled());
        metaField.setPrimary(dto.getPrimary());
        metaField.setFieldUsage(dto.getFieldUsage());
        metaField.setFilename(dto.getFilename());
        metaField.setUnique(dto.isUnique());

        if (dto.getValidationRule() != null) {
            validateAttributes(new ArrayList<ValidationRule>(Arrays.asList(dto.getValidationRule())));
            metaField.setValidationRule(dto.getValidationRule());
        }

        List<LanguageDTO> languages = languageDAO.findAll();
        for (LanguageDTO language : languages) {

//            if (dto.getDescription(language.getId()) != null) {
//                metaField.setDescription(dto.getDescription(language.getId()), language.getId());
//            }
        }
        metaField = metaFieldDAO.save(metaField);

        // save validation rule error messages after the meta field saving
        if (dto.getValidationRule() != null) {
            for (Map.Entry<Integer, String> entry : dto.getValidationRule().getErrors().entrySet()) {
                metaField.getValidationRule().setErrorMessage(entry.getKey(), entry.getValue());
            }
        }

        if (dto.getDefaultValue() != null) {
            MetaFieldValue value = dto.getDefaultValue();
            value.setField(metaField);
            metaFieldValueDAO.save(value);//does not cascade towards MetaField

            metaField.setDefaultValue(value);
            metaFieldDAO.save(metaField);//fire an update
        }

        return metaField;
    }

    @Override
    public void update(MetaField dto) {
        // TODO Auto-generated method stub
        Integer unUsedValidationRuleId = 0;
        Map<Integer, String> unUsedValidationErrorIds = new HashMap<Integer, String>();
        boolean removeUnused = false;

        MetaField metaField = metaFieldDAO.findById(dto.getId()).get();
        metaField.setName(dto.getName());
        metaField.setDisplayOrder(dto.getDisplayOrder());
        metaField.setMandatory(dto.isMandatory());
        metaField.setDisabled(dto.isDisabled());
        metaField.setPrimary(dto.getPrimary());
        metaField.setFieldUsage(dto.getFieldUsage());
        metaField.setFilename(dto.getFilename());
        metaField.setDataType(dto.getDataType());
        metaField.setUnique(dto.isUnique());

        if (metaField.getDefaultValue() != null && dto.getDefaultValue() == null) {
            metaField.getDefaultValue().setValue(null);
        } else if (dto.getDefaultValue() != null && metaField.getDefaultValue() == null) {
            MetaFieldValue value = metaField.createValue();
            value.setValue(dto.getDefaultValue().getValue());
            metaField.setDefaultValue(value);
        } else if (metaField.getDefaultValue() != null) {
            metaField.getDefaultValue().setValue(dto.getDefaultValue().getValue());
        }

        if (dto.getValidationRule() != null) {
            validateAttributes(new ArrayList<ValidationRule>(Arrays.asList(dto.getValidationRule())));
            if (metaField.getValidationRule() == null) {
                metaField.setValidationRule(dto.getValidationRule());
            } else {
                metaField.getValidationRule().setRuleType(dto.getValidationRule().getRuleType());
                metaField.getValidationRule().setRuleAttributes(dto.getValidationRule().getRuleAttributes());
            }
        }

        List<LanguageDTO> languages = languageDAO.findAll();
        for (LanguageDTO language : languages) {

//            if (dto.getDescription(language.getId()) != null) {
//                metaField.setDescription(dto.getDescription(language.getId()), language.getId());
//            }
        }

        metaFieldDAO.save(metaField);

        // update validation rule error messages after the meta field update
        if (dto.getValidationRule() != null) {
            for (Map.Entry<Integer, String> entry : dto.getValidationRule().getErrors().entrySet()) {
                metaField.getValidationRule().setErrorMessage(entry.getKey(), entry.getValue());
            }
        }

        // check if the rule has been removed
        if (metaField.getValidationRule() != null) {
            unUsedValidationRuleId = metaField.getValidationRule().getId();
            for (LanguageDTO language : languages) {
                if (metaField.getValidationRule().getErrorMessage(language.getId()) != null) {
                    if (dto.getValidationRule() == null) {
                        unUsedValidationErrorIds.put(language.getId(),
                                metaField.getValidationRule().getErrorMessage(language.getId()));
                    } else if (dto.getValidationRule().getErrors().get(language.getId()) == null) {
                        unUsedValidationErrorIds.put(language.getId(),
                                metaField.getValidationRule().getErrorMessage(language.getId()));
                    }
                }
            }
            if (dto.getValidationRule() == null) {
                ValidationRule rule = metaField.getValidationRule();
                rule.getErrors().clear();
                metaField.setValidationRule(null);
            }
            removeUnused = true;
        }


        //delete un-used links
        if (removeUnused && unUsedValidationRuleId != 0) {
            ValidationRule unUsedVr = validationRuleDAO.findById(unUsedValidationRuleId).get();
            if (unUsedVr != null && dto.getValidationRule() == null)
                validationRuleDAO.delete(unUsedVr);
/*
            InternationalDescriptionDAO idDas = InternationalDescriptionDAO.getInstance();
            JbillingTableDAS tableDas = Context.getBean(Context.Name.JBILLING_TABLE_DAS);
            JbillingTable table = tableDas.findByName(ServerConstants.TABLE_VALIDATION_RULE);

            for (Map.Entry<Integer, String> entry1 : unUsedValidationErrorIds.entrySet()) {
                idDas.delete(table.getId(),unUsedValidationRuleId,ValidationRule.ERROR_MSG_LABEL,entry1.getKey());
            }
            */
        }
    }

    @Override
    public void deleteIfNotParticipant(int metaFieldId) {
        // TODO Auto-generated method stub
        MetaField metaField = metaFieldDAO.findById(metaFieldId).get();
        if (metaField.getMetaFieldGroups() != null && metaField.getMetaFieldGroups().size() > 0) {
            String error = "MetaFieldValue,value,metafield.validation.inuse," + metaField.getId();
            throw new MetaFieldException(String.format("MetaField is in use in groups: %s .",
                    Arrays.toString(metaField.getMetaFieldGroups().toArray())), new String[]{error});
        }
        delete(metaFieldId);
    }

    @Override
    public void delete(int metaFieldId) {
        // TODO Auto-generated method stub
        MetaField metaField = metaFieldDAO.findById(metaFieldId).get();
        if (metaField.getDefaultValue() != null) {
            metaField.setDefaultValue(null);
            metaFieldDAO.save(metaField);
            metaFieldDAO.flush();
        }
        metaFieldDAO.deleteMetaFieldValuesForEntity(metaField.getEntityType(), metaFieldId);
        metaFieldDAO.flush();

        metaFieldDAO.delete(metaField);

        metaFieldDAO.flush();
    }

    @Override
    public MetaField getMetaField(Integer metafieldId) {
        // TODO Auto-generated method stub
        return metaFieldDAO.findById(metafieldId).get();

    }

    @Override
    public void validateAttributes(Collection<ValidationRule> models) {
        // TODO Auto-generated method stub
        List<String> errors = new ArrayList<String>();

        for (ValidationRule model : models) {
            try {
                AttributeUtils.validateRuleAttributes(model.getRuleAttributes(), model.getRuleType().getValidationRuleModel());
            } catch (Exception e) {
                errors.addAll(Arrays.asList(e.getMessage()));
            }
        }

        if (!errors.isEmpty()) {
            throw new MetaFieldException("Validation rule attributes failed validation.",
                    errors.toArray(new String[errors.size()]));

        }
    }

    @Override
    public void validateMetaFieldsChanges(Collection<MetaField> newMetaFields, Collection<MetaField> currentMetaFields) {
        // TODO Auto-generated method stub
        Map<Integer, MetaField> currentMetaFieldMap = new HashMap<Integer, MetaField>();
        Set<String> names = new HashSet<String>();

        //collect the current meta fields
        for (MetaField dto : currentMetaFields) {
            currentMetaFieldMap.put(dto.getId(), dto);
        }

        //loop through the new metaFields
        for (MetaField metaField : newMetaFields) {
            if (names.contains(metaField.getName())) {
                String[] errors = new String[]{"MetaFieldWS,name,metaField.validation.name.unique," + metaField.getName()};
                throw new MetaFieldException("Meta field names must be unique [" + metaField.getName() + "]", errors);
            }
            names.add(metaField.getName());

            //if it is already in the DB validate the changes
            if (metaField.getId() > 0) {
                MetaField currentMetaField = currentMetaFieldMap.get(metaField.getId());

                //if the type change we have to make sure it is not already used
                boolean checkUsage = !currentMetaField.getDataType().equals(metaField.getDataType());
                if (checkUsage && isMetaFieldUsed(EntityType.ORDER_LINE, metaField.getId())) {
                    String[] errors = new String[]{"MetaFieldWS,dataType,metaField.validation.type.change.not.allowed"};
                    throw new MetaFieldException("Data Type may not be changes is meta field is used [" + metaField.getName() + "]", errors);
                }
            }
        }
    }

    @Override
    public Collection<Integer> updateMetaFieldsCollection(Collection<MetaField> newMetaFields,
                                                          Collection<MetaField> currentMetaFields) {
        // TODO Auto-generated method stub
        Map<Integer, MetaField> currentMetaFieldMap = new HashMap<Integer, MetaField>();

        //collect the current meta fields
        for (MetaField dto : currentMetaFields) {
            currentMetaFieldMap.put(dto.getId(), dto);
        }
        // clear current collection for filling later
        currentMetaFields.clear();

        //loop through the new metaFields
        for (MetaField metaField : newMetaFields) {
            //if it is a saved status update the current object
            if (metaField.getId() > 0) {
                MetaField persistedMetaField = currentMetaFieldMap.remove(metaField.getId());
                mergeBasicProperties(persistedMetaField, metaField);

                update(persistedMetaField);
                currentMetaFields.add(persistedMetaField);
            } else {
                //else it is a new meta field and we must create it
                currentMetaFields.add(create(metaField));
            }
        }
        return currentMetaFieldMap.keySet();

    }

    @Override
    public List<MetaField> getMetaFields(Collection<Integer> entityIds, EntityType type) {
        // TODO Auto-generated method stub
        List<MetaField> availableFields = new ArrayList<MetaField>();

        for (Integer entityId : entityIds) {
            if (type != null) {
                availableFields.addAll(getAvailableFieldsList(entityId, new EntityType[]{type}));
            } else {
                availableFields.addAll(getAvailableFieldsList(entityId, new EntityType[]{EntityType.PRODUCT}));
            }
        }

        return availableFields;
    }

    @Override
    public MetaField getDTO(MetaFieldWS ws, Integer entityId) {
        // TODO Auto-generated method stub
        MetaField dto = new MetaField();
        dto.setDataType(ws.getDataType());
        dto.setId(ws.getId());

        if (ws.getDefaultValue() != null) {
            MetaFieldValue mfValue = dto.createValue();
            mfValue.setValue(ws.getDefaultValue().getValue());
            dto.setDefaultValue(mfValue);

        }
        dto.setDisabled(ws.isDisabled());
        dto.setDisplayOrder(ws.getDisplayOrder());
        dto.setEntityType(ws.getEntityType());
        dto.setEntity(new CompanyDTO(entityId));

        if (ws.getFieldUsage() != null) {
            dto.setFieldUsage(ws.getFieldUsage());
        }

        dto.setMandatory(ws.isMandatory());
        dto.setUnique(ws.isUnique());
        dto.setName(ws.getName());
        dto.setPrimary(ws.isPrimary());
        dto.setValidationRule(ws.getValidationRule() == null ? null : getValidationRuleDTO(ws.getValidationRule()));
        dto.setFilename(ws.getFilename());
        return dto;
    }

    @Override
    public MetaFieldValueWS getWS(MetaFieldValue metaFieldValue, Integer groupId) {
        // TODO Auto-generated method stub
        MetaFieldValueWS ws = getWS(metaFieldValue);
        ws.setGroupId(groupId);
        return ws;

    }

    @Override
    public MetaFieldWS getWS(MetaField dto) {
        // TODO Auto-generated method stub
        MetaFieldWS ws = new MetaFieldWS();
        ws.setDataType(dto.getDataType());
        if (dto.getDefaultValue() != null) {
            ws.setDefaultValue(getWS(dto.getDefaultValue()));
        }
        ws.setDisabled(dto.isDisabled());
        ws.setDisplayOrder(dto.getDisplayOrder());
        ws.setEntityId(dto.getEntity().getId());
        ws.setEntityType(dto.getEntityType());
        ws.setFieldUsage(dto.getFieldUsage());
        ws.setId(dto.getId());
        ws.setMandatory(dto.isMandatory());
        ws.setName(dto.getName());
        if (dto.getPrimary() != null) {
            ws.setPrimary(dto.getPrimary());
        }
        if (dto.getValidationRule() != null) {
            ws.setValidationRule(getValidationRuleWS(dto.getValidationRule()));
        }
        ws.setFilename(dto.getFilename());
        ws.setUnique(dto.isUnique());
        return ws;
    }

    @Override
    public MetaFieldValueWS getWS(@SuppressWarnings("rawtypes") MetaFieldValue metaFieldValue) {
        MetaFieldValueWS ws = new MetaFieldValueWS();
        if (metaFieldValue.getField() != null) {
            ws.setFieldName(metaFieldValue.getField().getName());
            ws.setDisabled(metaFieldValue.getField().isDisabled());
            ws.setMandatory(metaFieldValue.getField().isMandatory());
            ws.setDataType(metaFieldValue.getField().getDataType());
            ws.setDisplayOrder(metaFieldValue.getField().getDisplayOrder());
            ws.setDefaultValue(metaFieldValue.getField().getDefaultValue() != null ? metaFieldValue.getField().getDefaultValue().getValue() : null);
        }

        ws.setId(metaFieldValue.getId());
        ws.setValue(metaFieldValue.getValue());
        return ws;
    }


    @Override
    public ValidationRuleWS getValidationRuleWS(ValidationRule dto) {
        // TODO Auto-generated method stub
        ValidationRuleWS ws = new ValidationRuleWS();
        if (null != dto) {
            ws.setId(dto.getId());
            ws.setRuleType(dto.getRuleType().name());
            ws.setRuleAttributes(new TreeMap<String, String>(dto.getRuleAttributes()));
            ws.setEnabled(dto.isEnabled());

            List<LanguageDTO> languages = languageDAO.findAll();
            List<InternationalDescriptionWS> errors = new ArrayList<InternationalDescriptionWS>(1);

            for (LanguageDTO language : languages) {
                try {

//                    if (dto.getDescriptionDTO(language.getId(), ValidationRule.ERROR_MSG_LABEL) != null) {
//                        //errors.add(DescriptionBL.getInternationalDescriptionWS(dto.getDescriptionDTO(language.getId(),
//                        //      ValidationRule.ERROR_MSG_LABEL)));
//                    }
                } catch (Exception e) {
                    throw new MetaFieldException("Validation error message cannot be resolved for language: " + language);
                }
            }
            ws.setErrorMessages(errors);
        }
        return ws;

    }

    public ValidationRule getValidationRuleDTO(ValidationRuleWS ws) {
        if (null != ws) {

            ValidationRule rule = new ValidationRule();
            rule.setId(ws.getId());
            rule.setRuleType(ValidationRuleType.valueOf(ws.getRuleType()));
            rule.setRuleAttributes(ws.getRuleAttributes());
            if (ws.getErrorMessages() != null) {
                for (InternationalDescriptionWS desc : ws.getErrorMessages()) {
                    rule.addError(desc.getLanguageId(), desc.getContent());
                }
            }
            return rule;
        }
        return null;
    }

    public MetaFieldValue createValueFromDataType(MetaField metaField, Object value, DataType dataType) {

        try {

            switch (dataType) {
                case STRING:
                case STATIC_TEXT:
                case TEXT_AREA:
                case ENUMERATION:
                case SCRIPT:
                    StringMetaFieldValue stringMetaFieldValue = new StringMetaFieldValue(metaField);
                    if (value != null) {
                        stringMetaFieldValue.setValue(value.toString());
                    }
                    return stringMetaFieldValue;

                case INTEGER:
                    IntegerMetaFieldValue integerMetaFieldValue = new IntegerMetaFieldValue(metaField);
                    if (value != null) {
                        integerMetaFieldValue.setValue(Integer.valueOf(value.toString()));
                    }
                    return integerMetaFieldValue;

                case DECIMAL:
                    DecimalMetaFieldValue decimalMetaFieldValue = new DecimalMetaFieldValue(metaField);
                    if (value != null) {
                        decimalMetaFieldValue.setValue(new BigDecimal(value.toString()));
                    }
                    return decimalMetaFieldValue;

                case BOOLEAN:
                    BooleanMetaFieldValue booleanMetaFieldValue = new BooleanMetaFieldValue(metaField);
                    if (value != null) {
                        booleanMetaFieldValue.setValue(Boolean.valueOf(value.toString()));
                    }
                    return booleanMetaFieldValue;

                case DATE:
                    DateMetaFieldValue dateMetaFieldValue = new DateMetaFieldValue(metaField);
                    if (value != null) {
                        dateMetaFieldValue.setValue(DateTimeFormat.fullDate().parseDateTime(value.toString()).toDate());
                    }
                    return dateMetaFieldValue;

                case JSON_OBJECT:
                    JsonMetaFieldValue jsonMetaFieldValue = new JsonMetaFieldValue(metaField);
                    if (value != null) {
                        jsonMetaFieldValue.setValue(value.toString());
                    }
                    return jsonMetaFieldValue;

                case LIST:
                    ListMetaFieldValue listMetaFieldValue = new ListMetaFieldValue(metaField);
                    if (value != null) {
                        listMetaFieldValue.setValue(Arrays.asList(value.toString()));
                    }
                    return listMetaFieldValue;
            }

        } catch (Exception e) {
            // cant create a value//
            return null;
        }
        return null;
    }


}
