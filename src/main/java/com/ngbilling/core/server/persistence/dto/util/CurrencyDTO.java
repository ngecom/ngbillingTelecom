/*
 jBilling - The Enterprise Open Source Billing System
 Copyright (C) 2003-2011 Enterprise jBilling Software Ltd. and Emiliano Conde

 This file is part of jbilling.

 jbilling is free software: you can redistribute it and/or modify
 it under the terms of the GNU Affero General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 jbilling is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Affero General Public License for more details.

 You should have received a copy of the GNU Affero General Public License
 along with jbilling.  If not, see <http://www.gnu.org/licenses/>.

 This source was modified by Web Data Technologies LLP (www.webdatatechnologies.in) since 15 Nov 2015.
 You may download the latest source from webdataconsulting.github.io.

 */
package com.ngbilling.core.server.persistence.dto.util;


import com.ngbilling.core.payload.request.util.CurrencyWS;
import com.ngbilling.core.server.persistence.dto.invoice.InvoiceDTO;
import com.ngbilling.core.server.persistence.dto.order.OrderDTO;
import com.ngbilling.core.server.persistence.dto.partner.PartnerDTO;
import com.ngbilling.core.server.persistence.dto.payment.PaymentDTO;
import com.ngbilling.core.server.persistence.dto.process.ProcessRunTotalDTO;
import com.ngbilling.core.server.persistence.dto.user.CompanyDTO;
import com.ngbilling.core.server.persistence.dto.user.UserDTO;
import com.ngbilling.core.server.util.ServerConstants;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "currency")
@TableGenerator(
        name = "currency_GEN",
        table = "jbilling_seqs",
        pkColumnName = "name",
        valueColumnName = "next_id",
        pkColumnValue = "currency",
        allocationSize = 10
)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CurrencyDTO extends AbstractDescription implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int id;
    private String symbol;
    private String code;
    private String countryCode;
    private Set<CompanyDTO> entities = new HashSet<CompanyDTO>(0);
    private Set<UserDTO> baseUsers = new HashSet<UserDTO>(0);
    private Set<OrderDTO> orderDTOs = new HashSet<OrderDTO>(0);
    private Set<PartnerDTO> partners = new HashSet<PartnerDTO>(0);
    private Set<PaymentDTO> payments = new HashSet<PaymentDTO>(0);
    private Set<CurrencyExchangeDTO> currencyExchanges = new HashSet<CurrencyExchangeDTO>(0);
    private Set<CompanyDTO> entities_1 = new HashSet<CompanyDTO>(0);
    private Set<InvoiceDTO> invoices = new HashSet<InvoiceDTO>(0);
    private Set<ProcessRunTotalDTO> processRunTotals = new HashSet<ProcessRunTotalDTO>(0);
    private Integer versionNum;

    // from EX
    private String name = null;
    private Boolean inUse = null;
    private String rate = null; // will be converted to float
    private BigDecimal sysRate = null;

    public CurrencyDTO() {
    }

    // for stubs
    public CurrencyDTO(Integer id) {
        this.id = id;
    }

    public CurrencyDTO(int id, String symbol, String code, String countryCode) {
        this.id = id;
        this.symbol = symbol;
        this.code = code;
        this.countryCode = countryCode;
    }

    public CurrencyDTO(int id, String symbol, String code, String countryCode, Set<CompanyDTO> entities,
                       Set<UserDTO> baseUsers, Set<OrderDTO> orderDTOs, Set<PaymentDTO> payments,
                       Set<CurrencyExchangeDTO> currencyExchanges, Set<CompanyDTO> entities_1, Set<InvoiceDTO> invoices,
                       Set<ProcessRunTotalDTO> processRunTotals) {
        this.id = id;
        this.symbol = symbol;
        this.code = code;
        this.countryCode = countryCode;
        this.entities = entities;
        this.baseUsers = baseUsers;
        this.orderDTOs = orderDTOs;
        this.payments = payments;
        this.currencyExchanges = currencyExchanges;
        this.entities_1 = entities_1;
        this.invoices = invoices;
        this.processRunTotals = processRunTotals;
    }

    public CurrencyDTO(CurrencyWS ws) {
        if (ws.getId() != null) {
            this.id = ws.getId();
        }

        this.symbol = ws.getSymbol();
        this.code = ws.getCode();
        this.countryCode = ws.getCountryCode();
        this.inUse = ws.getInUse();

        if (StringUtils.isNotBlank(ws.getRate())) this.rate = ws.getRate();
        if (StringUtils.isNotBlank(ws.getSysRate())) this.sysRate = ws.getSysRateAsDecimal();
    }

    @Transient
    protected String getTable() {
        return ServerConstants.TABLE_CURRENCY;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "currency_GEN")
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "symbol", nullable = false, length = 10)
    public String getSymbol() {
        return this.symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Column(name = "code", nullable = false, length = 3)
    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "country_code", nullable = false, length = 2)
    public String getCountryCode() {
        return this.countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "currency")
    public Set<CompanyDTO> getEntities() {
        return this.entities;
    }

    public void setEntities(Set<CompanyDTO> entities) {
        this.entities = entities;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "currency")
    public Set<UserDTO> getBaseUsers() {
        return this.baseUsers;
    }

    public void setBaseUsers(Set<UserDTO> baseUsers) {
        this.baseUsers = baseUsers;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "currency")
    public Set<OrderDTO> getPurchaseOrders() {
        return this.orderDTOs;
    }

    public void setPurchaseOrders(Set<OrderDTO> orderDTOs) {
        this.orderDTOs = orderDTOs;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "currency")
    public Set<PaymentDTO> getPayments() {
        return this.payments;
    }

    public void setPayments(Set<PaymentDTO> payments) {
        this.payments = payments;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "currency", orphanRemoval = true)
    public Set<CurrencyExchangeDTO> getCurrencyExchanges() {
        return this.currencyExchanges;
    }

    public void setCurrencyExchanges(Set<CurrencyExchangeDTO> currencyExchanges) {
        this.currencyExchanges = currencyExchanges;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "currency_entity_map",
            joinColumns = {@JoinColumn(name = "currency_id", updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "entity_id", updatable = false)}
    )
    public Set<CompanyDTO> getEntities_1() {
        return this.entities_1;
    }

    public void setEntities_1(Set<CompanyDTO> entities_1) {
        this.entities_1 = entities_1;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "currency")
    public Set<InvoiceDTO> getInvoices() {
        return this.invoices;
    }

    public void setInvoices(Set<InvoiceDTO> invoices) {
        this.invoices = invoices;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "currency")
    public Set<ProcessRunTotalDTO> getProcessRunTotals() {
        return this.processRunTotals;
    }

    public void setProcessRunTotals(Set<ProcessRunTotalDTO> processRunTotals) {
        this.processRunTotals = processRunTotals;
    }

    @Version
    @Column(name = "OPTLOCK")
    public Integer getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(Integer versionNum) {
        this.versionNum = versionNum;
    }

    @Transient
    public Boolean getInUse() {
        return inUse;
    }

    public void setInUse(Boolean inUse) {
        this.inUse = inUse;
    }

    @Transient
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Transient
    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    @Transient
    public BigDecimal getRateAsDecimal() {
        return (rate == null ? null : new BigDecimal(rate));
    }

    @Transient
    public BigDecimal getSysRate() {
        return sysRate;
    }

    public void setSysRate(BigDecimal sysRate) {
        this.sysRate = sysRate;
    }

    @Transient
    public boolean isDeletable() {
        return (getEntities().isEmpty() && getBaseUsers().isEmpty()
                && getPurchaseOrders().isEmpty()
                && getPayments().isEmpty() && getInvoices().isEmpty()
                && getProcessRunTotals().isEmpty());
    }

}


