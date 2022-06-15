package com.ngbilling.core.server.persistence.dto.user;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * AccountTypePriceDTO composite primary key
 *
 * @author Panche Isajeski
 * @since 05/14/2013
 */
@Embeddable
public class AccountTypePricePK implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private AccountTypeDTO accountType;

    public AccountTypePricePK() {
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_type_id", nullable = false)
    public AccountTypeDTO getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountTypeDTO accountType) {
        this.accountType = accountType;
    }
}
