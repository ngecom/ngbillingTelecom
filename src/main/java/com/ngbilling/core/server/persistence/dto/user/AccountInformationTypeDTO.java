package com.ngbilling.core.server.persistence.dto.user;

import com.ngbilling.core.server.persistence.dto.metafield.MetaFieldGroup;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Account Information Type entity.
 *
 * @author Oleg Baskakov
 * @since 07-May-2013
 */
@Entity
@DiscriminatorValue("ACCOUNT_TYPE")
public class AccountInformationTypeDTO extends MetaFieldGroup implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Column(name = "name", nullable = false, length = 100)
    @NotEmpty(message = "validation.error.notnull")
    @Size(min = 1, max = 100, message = "validation.error.size,1,100")
    private String name;

    @ManyToOne(targetEntity = AccountTypeDTO.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "account_type_id", nullable = false)
    private AccountTypeDTO accountType;

    @Transient
    private boolean useForNotifications;

    public AccountInformationTypeDTO() {
        super();
    }

    public AccountInformationTypeDTO(String name,
                                     AccountTypeDTO accountType) {
        super();

        this.name = name;
        this.accountType = accountType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AccountTypeDTO getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountTypeDTO accountType) {
        this.accountType = accountType;
    }

    public boolean isUseForNotifications() {
        return useForNotifications;
    }

    public void setUseForNotifications(boolean useForNotifications) {
        this.useForNotifications = useForNotifications;
    }
}
