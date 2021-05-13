package com.ngbilling.core.server.validator.metafield;

/**
 * Package of available validation rule types
 *
 * @author Panche Isajeski
 */
public enum ValidationRuleType {

    EMAIL(new EmailValidationRuleModel()),
    RANGE(new RangeValidationRuleModel()),
    REGEX(new RegExValidationRuleModel()),
    SCRIPT(new ScriptValidationRuleModel()),
    PAYMENT_CARD(new PaymentCardValidationRuleModel());

    // CUSTOM (new CustomValidationRuleModel());;

    private final ValidationRuleModel validationRuleModel;

    ValidationRuleType(ValidationRuleModel validationRuleModel) {
        this.validationRuleModel = validationRuleModel;
    }

    public ValidationRuleModel getValidationRuleModel() {
        return validationRuleModel;
    }


}