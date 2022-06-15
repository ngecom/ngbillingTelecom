package com.ngbilling.core.server.persistence.dto.payment;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import com.ngbilling.core.server.persistence.dto.metafield.MetaField;


/**
 * @author khobab
 */
@Entity
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_method_template_GEN")
    @SequenceGenerator(
            name = "payment_method_template_GEN",
            allocationSize = 1
    )
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
