package com.ngbilling.core.server.persistence.dto.user;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * Account Type Pricing mapping.
 * Provides a list of prices per account type.
 *
 * @author Panche Isajeski
 * @since 05/14/2013
 */
@Entity
@Table(name = "account_type_price")
// TODO (pai) make the implementation more generic - it's almost the same as customer price
public class AccountTypePriceDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private AccountTypePricePK id = new AccountTypePricePK();
    private Date createDatetime = new Date();
    private Date priceExpiryDate = null;

    public AccountTypePriceDTO() {
    }

    public AccountTypePriceDTO(AccountTypePricePK id) {
        this.id = id;
    }

    @Id
    public AccountTypePricePK getId() {
        return id;
    }

    public void setId(AccountTypePricePK id) {
        this.id = id;
    }

    @Transient
    public AccountTypeDTO getAccountType() {
        return getId().getAccountType();
    }

    public void setAccountType(AccountTypeDTO accountType) {
        if (accountType != null) {
            id.setAccountType(accountType);
        }
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_datetime", nullable = false)
    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    @Column(name = "price_expiry_date", nullable = true)
    public Date getPriceExpiryDate() {
        return priceExpiryDate;
    }

    public void setPriceExpiryDate(Date priceExpiryDate) {
        this.priceExpiryDate = priceExpiryDate;
    }

    @Override
    public String toString() {
        return "AccountTypePriceDTO{" +
                "id=" + id +
                ", createDatetime=" + createDatetime +
                '}';
    }
}
