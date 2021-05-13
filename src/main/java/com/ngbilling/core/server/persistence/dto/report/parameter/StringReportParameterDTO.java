package com.ngbilling.core.server.persistence.dto.report.parameter;

import com.ngbilling.core.server.persistence.dto.report.ReportParameterDTO;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("string")
public class StringReportParameterDTO extends ReportParameterDTO<String> {

    private String value;

    @Transient
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
