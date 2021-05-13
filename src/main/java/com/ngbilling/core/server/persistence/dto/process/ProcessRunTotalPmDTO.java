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

import com.ngbilling.core.server.persistence.dto.payment.PaymentMethodDTO;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@TableGenerator(
        name = "process_run_total_pm_GEN",
        table = "jbilling_seqs",
        pkColumnName = "name",
        valueColumnName = "next_id",
        pkColumnValue = "process_run_total_pm",
        allocationSize = 100)
@Table(name = "process_run_total_pm")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ProcessRunTotalPmDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int id;
    private PaymentMethodDTO paymentMethod;
    private BigDecimal total;
    private ProcessRunTotalDTO processRunTotal;
    private int versionNum;

    public ProcessRunTotalPmDTO() {
    }


    public ProcessRunTotalPmDTO(int id, BigDecimal total) {
        this.id = id;
        this.total = total;
    }

    public ProcessRunTotalPmDTO(int id, PaymentMethodDTO paymentMethod, BigDecimal total) {
        this.id = id;
        this.paymentMethod = paymentMethod;
        this.total = total;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "process_run_total_pm_GEN")
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Column(name = "total", nullable = false, precision = 17, scale = 17)
    public BigDecimal getTotal() {
        return this.total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "process_run_total_id")
    public ProcessRunTotalDTO getProcessRunTotal() {
        return processRunTotal;
    }

    public void setProcessRunTotal(ProcessRunTotalDTO processRunTotal) {
        this.processRunTotal = processRunTotal;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_method_id")
    public PaymentMethodDTO getPaymentMethod() {
        return this.paymentMethod;
    }

    public void setPaymentMethod(PaymentMethodDTO paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Version
    @Column(name = "OPTLOCK")
    public int getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(int versionNum) {
        this.versionNum = versionNum;
    }

    public String toArray() {
        return " ProcessRunTotalPmDTO: id: " + id + " paymentMethod: " + paymentMethod +
                " total: " + total;
    }

}
