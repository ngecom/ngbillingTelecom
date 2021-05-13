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
package com.ngbilling.core.server.persistence.dto.process;


import com.ngbilling.core.server.persistence.dto.order.OrderPeriodDTO;
import com.ngbilling.core.server.persistence.dto.partner.PartnerDTO;
import com.ngbilling.core.server.persistence.dto.util.AbstractDescription;
import com.ngbilling.core.server.util.ServerConstants;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "period_unit")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class PeriodUnitDTO extends AbstractDescription implements java.io.Serializable {

    public static final int MONTH = 1;
    public static final int WEEK = 2;
    public static final int DAY = 3;
    public static final int YEAR = 4;
    public static final int SEMI_MONTHLY = 5;
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int id;
    private Set<PartnerDTO> partners = new HashSet<PartnerDTO>(0);
    private Set<OrderPeriodDTO> orderPeriodDTOs = new HashSet<OrderPeriodDTO>(0);
    private Set<BillingProcessDTO> billingProcesses = new HashSet<BillingProcessDTO>(0);
    private Set<BillingProcessConfigurationDTO> billingProcessConfigurations = new HashSet<BillingProcessConfigurationDTO>(0);

    public PeriodUnitDTO() {
    }

    public PeriodUnitDTO(int id) {
        this.id = id;
    }

    public PeriodUnitDTO(int id, Set<OrderPeriodDTO> orderPeriodDTOs, Set<BillingProcessDTO> billingProcesses, Set<BillingProcessConfigurationDTO> billingProcessConfigurations) {
        this.id = id;
        this.orderPeriodDTOs = orderPeriodDTOs;
        this.billingProcesses = billingProcesses;
        this.billingProcessConfigurations = billingProcessConfigurations;
    }

    @Transient
    protected String getTable() {
        return ServerConstants.TABLE_PERIOD_UNIT;
    }

    @Id
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "periodUnit")
    public Set<OrderPeriodDTO> getOrderPeriods() {
        return this.orderPeriodDTOs;
    }

    public void setOrderPeriods(Set<OrderPeriodDTO> orderPeriodDTOs) {
        this.orderPeriodDTOs = orderPeriodDTOs;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "periodUnit")
    public Set<BillingProcessDTO> getBillingProcesses() {
        return this.billingProcesses;
    }

    public void setBillingProcesses(Set<BillingProcessDTO> billingProcesses) {
        this.billingProcesses = billingProcesses;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "periodUnit")
    public Set<BillingProcessConfigurationDTO> getBillingProcessConfigurations() {
        return this.billingProcessConfigurations;
    }

    public void setBillingProcessConfigurations(Set<BillingProcessConfigurationDTO> billingProcessConfigurations) {
        this.billingProcessConfigurations = billingProcessConfigurations;
    }

    public String toString() {
        return "PeriodUnitDTO: " + id;
    }

}


