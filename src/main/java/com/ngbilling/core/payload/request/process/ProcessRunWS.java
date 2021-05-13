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

package com.ngbilling.core.payload.request.process;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ProcessRunWS
 *
 * @author Brian Cowdery
 * @since 25-10-2010
 */
public class ProcessRunWS implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer billingProcessId;
    private Date runDate;
    private Date started;
    private Date finished;
    private Integer invoicesGenerated;
    private Date paymentFinished;
    private List<ProcessRunTotalWS> processRunTotals = new ArrayList<ProcessRunTotalWS>(0);
    private Integer statusId;
    private String statusStr;

    public ProcessRunWS() {
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

    public Date getRunDate() {
        return runDate;
    }

    public void setRunDate(Date runDate) {
        this.runDate = runDate;
    }

    public Date getStarted() {
        return started;
    }

    public void setStarted(Date started) {
        this.started = started;
    }

    public Date getFinished() {
        return finished;
    }

    public void setFinished(Date finished) {
        this.finished = finished;
    }

    public Integer getInvoicesGenerated() {
        return invoicesGenerated;
    }

    public void setInvoicesGenerated(Integer invoicesGenerated) {
        this.invoicesGenerated = invoicesGenerated;
    }

    public Date getPaymentFinished() {
        return paymentFinished;
    }

    public void setPaymentFinished(Date paymentFinished) {
        this.paymentFinished = paymentFinished;
    }

    public List<ProcessRunTotalWS> getProcessRunTotals() {
        return processRunTotals;
    }

    public void setProcessRunTotals(List<ProcessRunTotalWS> processRunTotals) {
        this.processRunTotals = processRunTotals;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    @Override
    public String toString() {
        return "ProcessRunWS{"
                + "id=" + id
                + ", billingProcessId=" + billingProcessId
                + ", runDate=" + runDate
                + ", started=" + started
                + ", finished=" + finished
                + ", invoicesGenerated=" + invoicesGenerated
                + ", paymentFinished=" + paymentFinished
                + ", processRunTotals=" + (processRunTotals != null ? processRunTotals.size() : null)
                + ", statusId=" + statusId
                + ", statusStr='" + statusStr + '\''
                + '}';
    }
}
