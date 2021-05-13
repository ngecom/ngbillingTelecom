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


import com.ngbilling.core.common.util.Util;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * ProcessRunTotalWS
 *
 * @author Brian Cowdery
 * @since 25-10-2010
 */
public class ProcessRunTotalWS implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer processRunId;
    private Integer currencyId;
    private String totalInvoiced;
    private String totalPaid;
    private String totalNotPaid;

    public ProcessRunTotalWS() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProcessRunId() {
        return processRunId;
    }

    public void setProcessRunId(Integer processRunId) {
        this.processRunId = processRunId;
    }

    public Integer getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Integer currencyId) {
        this.currencyId = currencyId;
    }

    public String getTotalInvoiced() {
        return totalInvoiced;
    }

    public void setTotalInvoiced(String totalInvoiced) {
        this.totalInvoiced = totalInvoiced;
    }

    public void setTotalInvoiced(BigDecimal totalInvoiced) {
        this.totalInvoiced = (totalInvoiced != null ? totalInvoiced.toString() : null);
    }

    public BigDecimal getTotalInvoicedAsDecimal() {
        return Util.string2decimal(totalInvoiced);
    }

    public String getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(String totalPaid) {
        this.totalPaid = totalPaid;
    }

    public void setTotalPaid(BigDecimal totalPaid) {
        this.totalPaid = (totalPaid != null ? totalPaid.toString() : null);
    }

    public BigDecimal getTotalPaidAsDecimal() {
        return Util.string2decimal(totalPaid);
    }

    public String getTotalNotPaid() {
        return totalNotPaid;
    }

    public void setTotalNotPaid(String totalNotPaid) {
        this.totalNotPaid = totalNotPaid;
    }

    public void setTotalNotPaid(BigDecimal totalNotPaid) {
        this.totalNotPaid = (totalNotPaid != null ? totalNotPaid.toString() : null);
    }

    public BigDecimal getTotalNotPaidAsDecimal() {
        return Util.string2decimal(totalNotPaid);
    }

    @Override
    public String toString() {
        return "ProcessRunTotalWS{"
                + "id=" + id
                + ", processRunId=" + processRunId
                + ", currencyId=" + currencyId
                + ", totalInvoiced=" + totalInvoiced
                + ", totalPaid=" + totalPaid
                + ", totalNotPaid=" + totalNotPaid
                + '}';
    }
}
