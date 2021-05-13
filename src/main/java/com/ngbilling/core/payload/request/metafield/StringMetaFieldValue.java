package com.ngbilling.core.payload.request.metafield;

import com.ngbilling.core.server.persistence.dto.metafield.MetaField;
import com.ngbilling.core.server.persistence.dto.metafield.MetaFieldValue;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

@Entity
@DiscriminatorValue("string")
public class StringMetaFieldValue extends MetaFieldValue<String> {

    private String value;

    public StringMetaFieldValue() {
    }

    public StringMetaFieldValue(MetaField name) {
        super(name);
    }

    @Column(name = "string_value", nullable = true)
    @Size(max = 1000, message = "validation.error.size,1000")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    @Transient
    public boolean isEmpty() {
        return StringUtils.isEmpty(value);
    }
}
