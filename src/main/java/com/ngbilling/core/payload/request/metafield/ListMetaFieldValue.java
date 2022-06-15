package com.ngbilling.core.payload.request.metafield;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Transient;

import com.ngbilling.core.server.persistence.dto.metafield.MetaField;
import com.ngbilling.core.server.persistence.dto.metafield.MetaFieldValue;

@Entity
@DiscriminatorValue("list")
public class ListMetaFieldValue extends MetaFieldValue<List<String>> {

    private List<String> value = new ArrayList<String>();

    public ListMetaFieldValue() {
    }

    public ListMetaFieldValue(MetaField name) {
        super(name);
    }

    @ElementCollection(targetClass = String.class)
    @JoinTable(name = "list_meta_field_values",
            joinColumns = @JoinColumn(name = "meta_field_value_id")
    )
    @Column(name = "list_value")
    public List<String> getValue() {
        return value;
    }

    public void setValue(List<String> values) {
        this.value = values;
    }

    @Override
    @Transient
    public boolean isEmpty() {
        return value == null || value.isEmpty();
    }
}
