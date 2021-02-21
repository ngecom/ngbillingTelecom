package com.ngbilling.core.payload.request.metafield;



import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.ngbilling.core.server.persistence.dto.metafield.MetaField;
import com.ngbilling.core.server.persistence.dto.metafield.MetaFieldValue;

@Entity
@DiscriminatorValue("boolean")
public class BooleanMetaFieldValue extends MetaFieldValue<Boolean> {

    private Boolean value;

    public BooleanMetaFieldValue() {
    }

    public BooleanMetaFieldValue(MetaField name) {
        super(name);
    }

    @Column(name = "boolean_value", nullable = true)
    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }

    @Override
    @Transient
    public boolean isEmpty() {
        return value == null;
    }
}
