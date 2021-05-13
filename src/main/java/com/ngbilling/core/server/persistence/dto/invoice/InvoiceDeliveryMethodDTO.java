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
package com.ngbilling.core.server.persistence.dto.invoice;

import com.ngbilling.core.server.persistence.dto.user.CompanyDTO;
import com.ngbilling.core.server.persistence.dto.user.CustomerDTO;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@TableGenerator(
        name = "invoice_delivery_method_GEN",
        table = "jbilling_seqs",
        pkColumnName = "name",
        valueColumnName = "next_id",
        pkColumnValue = "invoice_delivery_method",
        allocationSize = 100)
@Table(name = "invoice_delivery_method")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class InvoiceDeliveryMethodDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int id;
    private Set<CompanyDTO> entities = new HashSet<CompanyDTO>(0);
    private Set<CustomerDTO> customers = new HashSet<CustomerDTO>(0);

    public InvoiceDeliveryMethodDTO() {
    }

    public InvoiceDeliveryMethodDTO(int id) {
        this.id = id;
    }

    public InvoiceDeliveryMethodDTO(int id, Set<CompanyDTO> entities,
                                    Set<CustomerDTO> customers) {
        this.id = id;
        this.entities = entities;
        this.customers = customers;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "invoice_delivery_method_GEN")
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "entity_delivery_method_map", joinColumns = {@JoinColumn(name = "method_id", updatable = false)}, inverseJoinColumns = {@JoinColumn(name = "entity_id", updatable = false)})
    public Set<CompanyDTO> getEntities() {
        return this.entities;
    }

    public void setEntities(Set<CompanyDTO> entities) {
        this.entities = entities;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "invoiceDeliveryMethod")
    public Set<CustomerDTO> getCustomers() {
        return this.customers;
    }

    public void setCustomers(Set<CustomerDTO> customers) {
        this.customers = customers;
    }

}
