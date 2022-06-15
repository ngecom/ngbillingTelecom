package com.ngbilling.core.server.validator.metafield;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.ngbilling.core.server.persistence.dto.metafield.ValidationRule;
import com.ngbilling.core.server.util.pricing.AttributeDefinition;

/**
 * Abstraction of the validation rule model
 * </p>
 * Provides attributes to be used by the validation rule models
 *
 * @author Panche Isajeski
 */
public abstract class AbstractValidationRuleModel<T> implements ValidationRuleModel<T> {

    private List<AttributeDefinition> attributeDefinitions = Collections.emptyList();

    @Override
    public List<AttributeDefinition> getAttributeDefinitions() {
        return attributeDefinitions;
    }

    public void setAttributeDefinitions(AttributeDefinition... attributeDefinitions) {
        this.attributeDefinitions = Collections.unmodifiableList(Arrays.asList(attributeDefinitions));
    }

    public boolean verifyValidationParameters(Object object, ValidationRule validationRule, Integer languageId) {

        if (null == object) {
            return false;
        } else if (object instanceof String && ((String) object).trim().isEmpty()) {
            return false;
        }
        if (null == validationRule) {
            return false;
        }

        String errorMessage = validationRule.getErrorMessage(languageId);

        if (null == errorMessage || errorMessage.isEmpty()) {
            return false;
        }

        return true;
    }

}
