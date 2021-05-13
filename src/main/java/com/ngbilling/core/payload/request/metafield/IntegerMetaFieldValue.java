package com.ngbilling.core.payload.request.metafield;

import com.ngbilling.core.server.persistence.dto.metafield.MetaField;
import com.ngbilling.core.server.persistence.dto.metafield.MetaFieldValue;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("integer")
public class IntegerMetaFieldValue extends MetaFieldValue<Integer> {

    private Integer value;

    public IntegerMetaFieldValue() {
    }

    public IntegerMetaFieldValue(MetaField name) {
        super(name);
    }

    @Column(name = "integer_value", nullable = true)
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    @Transient
    public boolean isEmpty() {
        return value == null;
    }
}