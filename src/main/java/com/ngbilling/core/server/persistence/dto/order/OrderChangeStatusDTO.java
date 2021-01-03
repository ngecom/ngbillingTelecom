/*
 * JBILLING CONFIDENTIAL
 * _____________________
 *
 * [2003] - [2013] Enterprise jBilling Software Ltd.
 * All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of Enterprise jBilling Software.
 * The intellectual and technical concepts contained
 * herein are proprietary to Enterprise jBilling Software
 * and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden.
 */

package com.ngbilling.core.server.persistence.dto.order;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.ngbilling.core.payload.request.order.ApplyToOrder;
import com.ngbilling.core.server.persistence.dto.user.CompanyDTO;
import com.ngbilling.core.server.persistence.dto.util.AbstractGenericStatus;
import com.ngbilling.core.server.util.ServerConstants;

/**
 * @author Alexander Aksenov
 * @since 05.07.13
 */
@Entity
@DiscriminatorValue("order_change_status")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OrderChangeStatusDTO extends AbstractGenericStatus implements java.io.Serializable {
    

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ApplyToOrder applyToOrder;
    private CompanyDTO company;
    private int deleted;

    public OrderChangeStatusDTO() {
        this.statusValue = 0;
    }

    @Column(name = "attribute1")
    @Enumerated(EnumType.STRING)
    public ApplyToOrder getApplyToOrder() {
        return applyToOrder;
    }

    public void setApplyToOrder(ApplyToOrder applyToOrder) {
        this.applyToOrder = applyToOrder;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entity_id")
    public CompanyDTO getCompany() {
        return company;
    }

    public void setCompany(CompanyDTO company) {
        this.company = company;
    }

    @Column(name = "deleted")
    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    @Transient
    protected String getTable() {
        return ServerConstants.TABLE_ORDER_CHANGE_STATUS;
    }

    @Override
    public String toString () {
        return "OrderChangeStatusDTO [applyToOrder=" + applyToOrder + ", company=" + company + ", deleted=" + deleted
                + "]";
    }
}
