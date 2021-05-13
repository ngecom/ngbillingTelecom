package com.ngbilling.core.server.validator.metafield;

import com.ngbilling.core.server.persistence.dto.metafield.ValidationRule;
import com.ngbilling.core.server.util.metafield.MetaContent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Email validation rule model
 * </p>
 * Validates that a value is an email address
 *
 * @author Panche Isajeski
 */
public class EmailValidationRuleModel extends AbstractValidationRuleModel {

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private Pattern pattern;
    private Matcher matcher;

    public EmailValidationRuleModel() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    @Override
    public ValidationReport doValidation(MetaContent source, Object object, ValidationRule validationRule, Integer languageId) {

        if (!verifyValidationParameters(object, validationRule, languageId)) {
            return null;
        }

        String errorMessage = validationRule.getErrorMessage(languageId);
        ValidationReport report = new ValidationReport();

        matcher = pattern.matcher(object.toString());
        if (!matcher.matches()) {
            report.addError("MetaFieldValue,value," + errorMessage);
        }

        return report;
    }
}
