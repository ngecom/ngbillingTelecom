package com.ngbilling.core.server.validator.metafield;

import com.ngbilling.core.server.persistence.dto.metafield.MetaFieldValue;
import com.ngbilling.core.server.persistence.dto.metafield.ValidationRule;
import com.ngbilling.core.server.util.metafield.MetaContent;

/**
 *  Example validation using source dependency
 *  </p>
 *  The validation of the current object value depends on some of the source meta-fields
 *
 *  @author Panche Isajeski
 */
public class SourceDependantValidationRuleModel extends AbstractValidationRuleModel {

    @Override
    public ValidationReport doValidation(MetaContent source, Object object, ValidationRule validationRule, Integer languageId) {

        if (!verifyValidationParameters(object, validationRule, languageId)) {
            return null;
        }

        String errorMessage = validationRule.getErrorMessage(languageId);
        ValidationReport report = new ValidationReport();

        // get metafield from the source
        MetaFieldValue metaFieldValue = source.getMetaField("validationName", 1);
        if (metaFieldValue != null) {
            if (object == null) {
                report.addError("MetaFieldValue,value," + errorMessage);
            }
        }

        return report;
    }
}
