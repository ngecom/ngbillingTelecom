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

package com.ngbilling.core.payload.request.order;


import java.io.Serializable;
import java.util.Date;

/**
 * OrderProcessWS
 *
 * @author Brian Cowdery
 * @since 25-10-2010
 */
public class OrderProcessWS implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer billingProcessId;
    private Integer orderId;
    private Integer invoiceId;
    private Integer periodsIncluded;
    private Date periodStart;
    private Date periodEnd;
    private Integer isReview;
    private Integer origin;

    public OrderProcessWS() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBillingProcessId() {
        return billingProcessId;
    }

    public void setBillingProcessId(Integer billingProcessId) {
        this.billingProcessId = billingProcessId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Integer getPeriodsIncluded() {
        return periodsIncluded;
    }

    public void setPeriodsIncluded(Integer periodsIncluded) {
        this.periodsIncluded = periodsIncluded;
    }

    public Date getPeriodStart() {
        return periodStart;
    }

    public void setPeriodStart(Date periodStart) {
        this.periodStart = periodStart;
    }

    public Date getPeriodEnd() {
        return periodEnd;
    }

    public void setPeriodEnd(Date periodEnd) {
        this.periodEnd = periodEnd;
    }

    public Integer getReview() {
        return isReview;
    }

    public void setReview(Integer review) {
        isReview = review;
    }

    public Integer getOrigin() {
        return origin;
    }

    public void setOrigin(Integer origin) {
        this.origin = origin;
    }

    @Override
    public String toString() {
        return "OrderProcessWS{"
                + "id=" + id
                + ", orderId=" + orderId
                + ", invoiceId=" + invoiceId
                + ", periodsIncluded=" + periodsIncluded
                + ", periodStart=" + periodStart
                + ", periodEnd=" + periodEnd
                + ", isReview=" + isReview
                + ", origin=" + origin
                + '}';
    }
}
