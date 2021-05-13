package com.ngbilling.core.payload.request.metafield;

import com.ngbilling.core.server.persistence.dto.metafield.MetaField;
import com.ngbilling.core.server.persistence.dto.metafield.MetaFieldValue;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("decimal")
public class DecimalMetaFieldValue extends MetaFieldValue<BigDecimal> {

    private BigDecimal value;

    public DecimalMetaFieldValue() {
    }

    public DecimalMetaFieldValue(MetaField name) {
        super(name);
    }

    @Column(name = "decimal_value", nullable = true, precision = 10, scale = 22)
    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    @Transient
    public boolean isEmpty() {
        return value == null;
    }
}