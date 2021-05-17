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


import com.ngbilling.core.server.persistence.dao.user.CompanyDAO;
import com.ngbilling.core.server.persistence.dto.process.PeriodUnitDTO;
import com.ngbilling.core.server.persistence.dto.user.CompanyDTO;
import com.ngbilling.core.server.persistence.dto.util.AbstractDescription;
import com.ngbilling.core.server.util.ServerConstants;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "order_period")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OrderPeriodDTO extends AbstractDescription implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int id;
    private CompanyDTO company;
    private PeriodUnitDTO periodUnitDTO;
    private Integer value;
    private Set<OrderDTO> orderDTOs = new HashSet<OrderDTO>(0);
    private Integer versionNum;
    @Autowired
    private CompanyDAO companyDAO;

    public OrderPeriodDTO() {
    }

    public OrderPeriodDTO(int id) {
        this.id = id;
    }

    public OrderPeriodDTO(Integer unitId, Integer value, Integer entityId) {
        setUnitId(unitId);
        setValue(value);
        this.company = companyDAO.findById(entityId).get();
    }

    public OrderPeriodDTO(CompanyDTO entity, PeriodUnitDTO periodUnitDTO, Integer value) {
        this.company = entity;
        this.periodUnitDTO = periodUnitDTO;
        this.value = value;
    }

    public OrderPeriodDTO(int id, CompanyDTO entity, PeriodUnitDTO periodUnitDTO, Integer value, Set<OrderDTO> orderDTOs) {
        this.id = id;
        this.company = entity;
        this.periodUnitDTO = periodUnitDTO;
        this.value = value;
        this.orderDTOs = orderDTOs;
    }

    @Transient
    protected String getTable() {
        return ServerConstants.TABLE_ORDER_PERIOD;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_period_GEN")
    @SequenceGenerator(
            name = "order_period_GEN",
            allocationSize = 1
    )
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entity_id")
    public CompanyDTO getCompany() {
        return this.company;
    }

    public void setCompany(CompanyDTO entity) {
        this.company = entity;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id")
    public PeriodUnitDTO getPeriodUnit() {
        return this.periodUnitDTO;
    }

    public void setPeriodUnit(PeriodUnitDTO periodUnitDTO) {
        this.periodUnitDTO = periodUnitDTO;
    }

    @Column(name = "value")
    public Integer getValue() {
        return this.value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "orderPeriod")
    public Set<OrderDTO> getPurchaseOrders() {
        return this.orderDTOs;
    }

    public void setPurchaseOrders(Set<OrderDTO> orderDTOs) {
        this.orderDTOs = orderDTOs;
    }

    @Version
    @Column(name = "OPTLOCK")
    public Integer getVersionNum() {
        return versionNum;
    }

    protected void setVersionNum(Integer versionNum) {
        this.versionNum = versionNum;
    }

    @Override
    public String toString() {
        return "OrderPeriodDTO:[" +
                " id=" + id +
                " company=" + company +
                " periodUnitDTO=" + periodUnitDTO +
                " value=" + value +
                " versionNum=" + versionNum + "]";
    }

    // convenient methods for migration from entity beans
    @Transient
    public Integer getUnitId() {
        if (getPeriodUnit() != null)
            return getPeriodUnit().getId();

        return null;
    }

    public void setUnitId(int id) {
        PeriodUnitDTO period = new PeriodUnitDTO(id);
        setPeriodUnit(period);
    }

    @Transient
    public boolean isOneTime() {
        return (id == ServerConstants.ORDER_PERIOD_ONCE.intValue());
    }

    @Transient
    public boolean isRecurring() {
        return (id != ServerConstants.ORDER_PERIOD_ONCE.intValue());
    }

    public void touch() {
        getUnitId();
    }

}


