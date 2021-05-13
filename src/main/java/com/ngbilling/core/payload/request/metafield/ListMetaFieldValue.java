package com.ngbilling.core.payload.request.metafield;

import com.ngbilling.core.server.persistence.dto.metafield.MetaField;
import com.ngbilling.core.server.persistence.dto.metafield.MetaFieldValue;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
