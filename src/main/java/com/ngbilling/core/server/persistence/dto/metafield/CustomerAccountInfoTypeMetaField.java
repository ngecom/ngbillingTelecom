package com.ngbilling.core.server.persistence.dto.metafield;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ngbilling.core.server.persistence.dto.user.AccountInformationTypeDTO;
import com.ngbilling.core.server.persistence.dto.user.CustomerDTO;

@Entity
@Table(name = "customer_account_info_type_timeline")
public class CustomerAccountInfoTypeMetaField implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_account_info_type_timeline_GEN")
    @SequenceGenerator(
            name = "customer_account_info_type_timeline_GEN",
            allocationSize = 1
    )
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerDTO customer;

    @ManyToOne
    @JoinColumn(name = "account_info_type_id", nullable = false)
    private AccountInformationTypeDTO accountInfoType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "meta_field_value_id", nullable = false)
    private MetaFieldValue metaFieldValue;

    @Temporal(TemporalType.DATE)
    @Column(name = "effective_date", nullable = false, length = 10)
    private Date effectiveDate;

    public CustomerAccountInfoTypeMetaField() {
    }

    public CustomerAccountInfoTypeMetaField(CustomerDTO customer, AccountInformationTypeDTO accountInfoType, MetaFieldValue value, Date effectiveDate) {
        this.customer = customer;
        this.accountInfoType = accountInfoType;
        this.metaFieldValue = value;
        this.effectiveDate = effectiveDate;
    }

    public AccountInformationTypeDTO getAccountInfoType() {
        return accountInfoType;
    }

    public MetaFieldValue getMetaFieldValue() {
        return metaFieldValue;
    }

    public void setMetaFieldValue(MetaFieldValue value) {
        this.metaFieldValue = value;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }
}
