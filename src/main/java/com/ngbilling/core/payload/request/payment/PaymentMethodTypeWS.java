package com.ngbilling.core.payload.request.payment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.ToString;

@ToString
public class PaymentMethodTypeWS implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Integer id;
    @Size(max = 20, message = "validation.error.length.max,20")
    @NotNull(message = "validation.error.is.required")
    private String methodName;
    private Boolean isRecurring;
    private Boolean allAccountType;
    private Integer templateId;

    private List<Integer> accountTypes = new ArrayList<Integer>();

    @NotNull(message = "validation.error.notnull")
    @NotNull(message = "validation.error.notnull")

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Boolean isAllAccountType() {
        return allAccountType;
    }

    public void setAllAccountType(Boolean isAllAccountType) {
        this.allAccountType = isAllAccountType;
    }

    public Boolean getIsRecurring() {
        return isRecurring;
    }

    public void setIsRecurring(Boolean isRecurring) {
        this.isRecurring = isRecurring;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public List<Integer> getAccountTypes() {
        return accountTypes;
    }

    public void setAccountTypes(List<Integer> accountTypes) {
        this.accountTypes = accountTypes;
    }

}
