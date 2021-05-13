package com.ngbilling.core.server.validator.metafield;

import com.ngbilling.core.common.util.FormatLogger;
import com.ngbilling.core.server.persistence.dto.metafield.ValidationRule;
import com.ngbilling.core.server.util.metafield.MetaContent;
import com.ngbilling.core.server.util.pricing.AttributeDefinition;
import com.ngbilling.core.server.util.pricing.AttributeDefinition.Type;
import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.HibernateValidatorConfiguration;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Script validation rule model
 * </p>
 * Uses grails script to validate a value of an object
 *
 * @author Panche Isajeski
 */
public class ScriptValidationRuleModel extends AbstractValidationRuleModel {

    private static final FormatLogger LOG = new FormatLogger(ScriptValidationRuleModel.class);
    public static String VALIDATION_SCRIPT_FIELD = "validationScript";


    public ScriptValidationRuleModel() {

        setAttributeDefinitions(
                new AttributeDefinition(VALIDATION_SCRIPT_FIELD, Type.STRING, true)
        );
    }

    @Override
    public ValidationReport doValidation(MetaContent source, Object object, ValidationRule validationRule, Integer languageId) {

        if (!verifyValidationParameters(object, validationRule, languageId)) {
            return null;
        }

        List<String> errors;
        try {
            String errorMessage = validationRule.getErrorMessage(languageId);

//            DefaultConstraintMapping mapping = new DefaultConstraintMapping();
//            mapping.type(object.getClass())
//                    .constraint(new ScriptAssertDef()
//                    .message(errorMessage)
//                    .script(getValidationScript(validationRule))
//                    .lang("groovy"));
            HibernateValidatorConfiguration config =
                    Validation.byProvider(HibernateValidator.class).configure();
//            config.addMapping(mapping);

            ValidatorFactory factory = config.buildValidatorFactory();
            Validator validator = factory.getValidator();

            Set<ConstraintViolation<Object>> constraintViolations = validator
                    .validate(object);

            errors = getErrorMessages(constraintViolations);
        } catch (Exception e) {
            LOG.debug("Invalid script exception : ", e);
            e.printStackTrace();

            errors = new ArrayList<String>(Arrays.asList("bean.ScriptValidationRuleModel.invalid.message"));
        }


        ValidationReport report = new ValidationReport();
        report.setErrors(errors);
        return report;
    }

    private List<String> getErrorMessages(
            Set<ConstraintViolation<Object>> constraintViolations) {

        List<String> errors = new ArrayList<String>();
        for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
            errors.add("MetaFieldValue,value," + constraintViolation.getMessage());
        }
        return errors;
    }

    public String getValidationScript(ValidationRule validationRule) {
        return validationRule.getRuleAttributes().get(VALIDATION_SCRIPT_FIELD);
    }
}
