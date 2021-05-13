package com.ngbilling.core.server.validator.metafield;

import com.ngbilling.core.server.persistence.dto.metafield.ValidationRule;
import com.ngbilling.core.server.util.metafield.MetaContent;
import com.ngbilling.core.server.util.pricing.AttributeDefinition;
import com.ngbilling.core.server.util.pricing.AttributeDefinition.Type;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validation using regular expression provided as a parameter
 *
 * @author Panche Isajeski
 */
public class RegExValidationRuleModel extends AbstractValidationRuleModel {

    public static String VALIDATION_REG_EX_FIELD = "regularExpression";

    private Pattern pattern;
    private Matcher matcher;

    public RegExValidationRuleModel() {

        setAttributeDefinitions(
                new AttributeDefinition(VALIDATION_REG_EX_FIELD, Type.STRING, true)
        );
    }

    @Override
    public ValidationReport doValidation(MetaContent source, Object object, ValidationRule validationRule, Integer languageId) {

        if (!verifyValidationParameters(object, validationRule, languageId)) {
            return null;
        }

        String errorMessage = validationRule.getErrorMessage(languageId);
        ValidationReport report = new ValidationReport();

        pattern = Pattern.compile(getValidationRegExField(validationRule));
        matcher = pattern.matcher(object.toString());
        if (!matcher.matches()) {
            report.addError("MetaFieldValue,value," + errorMessage);
        }

        return report;

    }

    public String getValidationRegExField(ValidationRule validationRule) {
        return validationRule.getRuleAttributes().get(VALIDATION_REG_EX_FIELD);
    }
}
