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
package com.ngbilling.core.server.persistence.dto.payment;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.ngbilling.core.server.persistence.dto.process.ProcessRunTotalPmDTO;
import com.ngbilling.core.server.persistence.dto.user.CompanyDTO;
import com.ngbilling.core.server.persistence.dto.util.AbstractDescription;
import com.ngbilling.core.server.util.ServerConstants;

@Entity
@Table(name = "payment_method")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class PaymentMethodDTO extends AbstractDescription implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int id;
    private Set<PaymentDTO> payments = new HashSet<PaymentDTO>(0);
    private Set<CompanyDTO> entities = new HashSet<CompanyDTO>(0);
    private Set<ProcessRunTotalPmDTO> processRunTotalPms = new HashSet<ProcessRunTotalPmDTO>(
            0);

    public PaymentMethodDTO() {
    }

    public PaymentMethodDTO(int id) {
        setId(id);
    }

    public PaymentMethodDTO(int id, Set<PaymentDTO> payments,
                            Set<CompanyDTO> entities,
                            Set<ProcessRunTotalPmDTO> processRunTotalPms) {
        this.id = id;
        this.payments = payments;
        this.entities = entities;
        this.processRunTotalPms = processRunTotalPms;
    }

    @Id
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Transient
    protected String getTable() {
        return ServerConstants.TABLE_PAYMENT_METHOD;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "paymentMethod")
    public Set<PaymentDTO> getPayments() {
        return this.payments;
    }

    public void setPayments(Set<PaymentDTO> payments) {
        this.payments = payments;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "entity_payment_method_map", joinColumns = {@JoinColumn(name = "payment_method_id", updatable = false)}, inverseJoinColumns = {@JoinColumn(name = "entity_id", updatable = false)})
    public Set<CompanyDTO> getEntities() {
        return this.entities;
    }

    public void setEntities(Set<CompanyDTO> entities) {
        this.entities = entities;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "paymentMethod")
    public Set<ProcessRunTotalPmDTO> getProcessRunTotalPms() {
        return this.processRunTotalPms;
    }

    public void setProcessRunTotalPms(
            Set<ProcessRunTotalPmDTO> processRunTotalPms) {
        this.processRunTotalPms = processRunTotalPms;
    }

}
