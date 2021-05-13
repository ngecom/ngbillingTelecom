package com.ngbilling.core.server.validator.metafield;

import java.util.ArrayList;
import java.util.List;

/**
 * Reports an error during the validation
 *
 * @author Panche Isajeski
 */
public class ValidationReport {

    List<String> errors = new ArrayList<String>();

    public ValidationReport() {
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public void addError(String error) {
        this.errors.add(error);
    }
}
