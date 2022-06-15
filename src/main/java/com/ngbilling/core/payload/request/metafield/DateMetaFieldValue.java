package com.ngbilling.core.payload.request.metafield;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.ngbilling.core.server.persistence.dto.metafield.MetaField;
import com.ngbilling.core.server.persistence.dto.metafield.MetaFieldValue;

@Entity
@DiscriminatorValue("date")
public class DateMetaFieldValue extends MetaFieldValue<Date> {

    private Date value;

    public DateMetaFieldValue() {
    }

    public DateMetaFieldValue(MetaField name) {
        super(name);
    }

    @Column(name = "date_value", nullable = true)
    public Date getValue() {
        return value;
    }

    public void setValue(Date value) {
        this.value = value;
    }

    @Override
    @Transient
    public boolean isEmpty() {
        return value == null;
    }
}
