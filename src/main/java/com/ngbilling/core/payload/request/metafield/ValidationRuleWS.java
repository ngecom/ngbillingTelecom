package com.ngbilling.core.payload.request.metafield;

import java.io.Serializable;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.validation.constraints.NotNull;

import com.ngbilling.core.payload.request.util.InternationalDescriptionWS;



/**
 * WS class from ValidationRule
 *
 *  @author Panche Isajeski
 */
public class ValidationRuleWS implements Serializable {
	
	public static final String ERROR_MSG_LABEL= "errorMessage";
    private int id;
    @NotNull(message = "validation.error.null.rule.type")
    private String ruleType;
    private SortedMap<String, String> ruleAttributes = new TreeMap<String, String>();

    @NotNull(message = "validation.error.empty.error.message")
    private List<InternationalDescriptionWS> errorMessages = null;
    private boolean enabled = true;

    public ValidationRuleWS() {
    }

   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRuleType() {
        return ruleType;
    }

    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }

    public SortedMap<String, String> getRuleAttributes() {
        return ruleAttributes;
    }

    public void setRuleAttributes(SortedMap<String, String> ruleAttributes) {
        this.ruleAttributes = ruleAttributes;
    }

    public void addRuleAttribute(String name, String value) {
        this.ruleAttributes.put(name, value);
    }

    public List<InternationalDescriptionWS> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(List<InternationalDescriptionWS> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public void addErrorMessage(int langId, String errorMessage) {
        InternationalDescriptionWS errorMessageWS=new InternationalDescriptionWS(ERROR_MSG_LABEL, langId, errorMessage);
        this.errorMessages.add(errorMessageWS);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "ValidationRuleWS{" +
                "id=" + id +
                ", ruleType=" + ruleType +
                ", ruleAttributes=" + ruleAttributes +
                ", errorMessages=" + errorMessages +
                ", enabled=" + enabled +
                '}';
    }
}
