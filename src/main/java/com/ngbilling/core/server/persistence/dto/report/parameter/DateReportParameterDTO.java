package com.ngbilling.core.server.persistence.dto.report.parameter;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.ngbilling.core.server.persistence.dto.report.ReportParameterDTO;


/**
 * DateReportParameterDTO
 *
 * @author Brian Cowdery
 * @since 07/03/11
 */
@Entity
@DiscriminatorValue("date")
public class DateReportParameterDTO extends ReportParameterDTO<Date> {

    private Date value;

    @Transient
    public Date getValue() {
        return value;
    }

    public void setValue(Date value) {
        this.value = value;
    }
}