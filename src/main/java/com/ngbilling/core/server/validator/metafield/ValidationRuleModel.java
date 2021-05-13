package com.ngbilling.core.server.validator.metafield;

import com.ngbilling.core.server.persistence.dto.metafield.ValidationRule;
import com.ngbilling.core.server.util.metafield.MetaContent;
import com.ngbilling.core.server.util.pricing.AttributeDefinition;

import java.util.List;

/**
 * Defines models for validation rules;
 * Performs validation on entities
 *
 * @author Panche Isajeski
 */
public interface ValidationRuleModel<T> {

    public List<AttributeDefinition> getAttributeDefinitions();

    public ValidationReport doValidation(MetaContent source, T object, ValidationRule validationRule, Integer languageId);

}
