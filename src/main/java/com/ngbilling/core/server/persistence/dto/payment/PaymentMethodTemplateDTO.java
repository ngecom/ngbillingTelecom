package com.ngbilling.core.server.persistence.dto.payment;

import com.ngbilling.core.server.persistence.dto.metafield.MetaField;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


/**
 * @author khobab
 */
@Entity
@TableGenerator(
        name = "payment_method_template_GEN",
        table = "jbilling_seqs",
        pkColumnName = "name",
        valueColumnName = "next_id",
        pkColumnValue = "payment_method_template",
        allocationSize = 10
)
@Table(name = "payment_method_template")
public class PaymentMethodTemplateDTO implements Serializable {

    private static final long serialVersionUID = 6278737291727940386L;

    private int id;
    private String templateName;

    private int version;

    private Set<MetaField> paymentTemplateMetaFields;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "payment_method_template_meta_fields_map",
            joinColumns = @JoinColumn(name = "method_template_id", updatable = false),
            inverseJoinColumns = @JoinColumn(name = "meta_field_id", updatable = false)
    )
    @OrderBy("displayOrder")
    public Set<MetaField> getPaymentTemplateMetaFields() {
        return paymentTemplateMetaFields;
    }

    public void setPaymentTemplateMetaFields(Set<MetaField> paymentTemplateMetaFields) {
        this.paymentTemplateMetaFields = paymentTemplateMetaFields;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "payment_method_template_GEN")
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addPaymentTemplateMetaField(MetaField paymentTemplateMetaField) {
        if (this.paymentTemplateMetaFields == null)
            this.paymentTemplateMetaFields = new HashSet<MetaField>();

        this.paymentTemplateMetaFields.add(paymentTemplateMetaField);
    }


    @Column(name = "template_name", length = 50)
    public String getTemplateName() {
        return this.templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    @Version
    @Column(name = "OPTLOCK")
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

}
