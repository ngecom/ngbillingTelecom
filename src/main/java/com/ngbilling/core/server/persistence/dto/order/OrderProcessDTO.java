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


import com.ngbilling.core.server.persistence.dto.invoice.InvoiceDTO;
import com.ngbilling.core.server.persistence.dto.process.BillingProcessDTO;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Date;

@Entity
@TableGenerator(
        name = "order_process_GEN",
        table = "jbilling_seqs",
        pkColumnName = "name",
        valueColumnName = "next_id",
        pkColumnValue = "order_process",
        allocationSize = 100
)
@Table(name = "order_process")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OrderProcessDTO implements java.io.Serializable {


    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int id;
    private BillingProcessDTO billingProcessDTO;
    private OrderDTO orderDTO;
    private InvoiceDTO invoiceDTO;
    private Integer periodsIncluded;
    private Date periodStart;
    private Date periodEnd;
    private int isReview;
    private Integer origin;
    private Integer versionNum;


    public OrderProcessDTO() {
    }


    public OrderProcessDTO(int id, int isReview) {
        this.id = id;
        this.isReview = isReview;
    }

    public OrderProcessDTO(int id, BillingProcessDTO billingProcessDTO, OrderDTO orderDTO, InvoiceDTO invoice,
                           Integer periodsIncluded, Date periodStart, Date periodEnd, int isReview, Integer origin) {
        this.id = id;
        this.billingProcessDTO = billingProcessDTO;
        this.orderDTO = orderDTO;
        this.invoiceDTO = invoice;
        this.periodsIncluded = periodsIncluded;
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
        this.isReview = isReview;
        this.origin = origin;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "order_process_GEN")
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "billing_process_id")
    public BillingProcessDTO getBillingProcess() {
        return this.billingProcessDTO;
    }

    public void setBillingProcess(BillingProcessDTO billingProcessDTO) {
        this.billingProcessDTO = billingProcessDTO;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    public OrderDTO getPurchaseOrder() {
        return this.orderDTO;
    }

    public void setPurchaseOrder(OrderDTO orderDTO) {
        this.orderDTO = orderDTO;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id")
    public InvoiceDTO getInvoice() {
        return this.invoiceDTO;
    }

    public void setInvoice(InvoiceDTO invoice) {
        this.invoiceDTO = invoice;
    }

    @Column(name = "periods_included")
    public Integer getPeriodsIncluded() {
        return this.periodsIncluded;
    }

    public void setPeriodsIncluded(Integer periodsIncluded) {
        this.periodsIncluded = periodsIncluded;
    }

    @Column(name = "period_start", length = 13)
    public Date getPeriodStart() {
        return this.periodStart;
    }

    public void setPeriodStart(Date periodStart) {
        this.periodStart = periodStart;
    }

    @Column(name = "period_end", length = 13)
    public Date getPeriodEnd() {
        return this.periodEnd;
    }

    public void setPeriodEnd(Date periodEnd) {
        this.periodEnd = periodEnd;
    }

    @Column(name = "is_review", nullable = false)
    public int getIsReview() {
        return this.isReview;
    }

    public void setIsReview(int isReview) {
        this.isReview = isReview;
    }

    @Column(name = "origin")
    public Integer getOrigin() {
        return this.origin;
    }

    public void setOrigin(Integer origin) {
        this.origin = origin;
    }

    @Version
    @Column(name = "OPTLOCK")
    public Integer getVersionNum() {
        return versionNum;
    }

    protected void setVersionNum(Integer versionNum) {
        this.versionNum = versionNum;
    }

}


