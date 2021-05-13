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
package com.ngbilling.core.server.persistence.dto.order;


import com.ngbilling.core.server.persistence.dto.util.AbstractDescription;
import com.ngbilling.core.server.util.ServerConstants;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "order_billing_type")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class OrderBillingTypeDTO extends AbstractDescription implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int id;
    private Set<OrderDTO> orderDTOs = new HashSet<OrderDTO>(0);

    public OrderBillingTypeDTO() {
    }

    public OrderBillingTypeDTO(int id) {
        this.id = id;
    }

    public OrderBillingTypeDTO(int id, Set<OrderDTO> orderDTOs) {
        this.id = id;
        this.orderDTOs = orderDTOs;
    }

    @Transient
    public String getTable() {
        return ServerConstants.TABLE_ORDER_BILLING_TYPE;
    }

    @Id
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "orderBillingType")
    public Set<OrderDTO> getPurchaseOrders() {
        return this.orderDTOs;
    }

    public void setPurchaseOrders(Set<OrderDTO> orderDTOs) {
        this.orderDTOs = orderDTOs;
    }

    public String toString() {
        return " OrderBillingType=" + id;
    }

}


