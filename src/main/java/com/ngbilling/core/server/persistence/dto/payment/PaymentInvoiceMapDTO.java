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

package com.ngbilling.core.server.persistence.dto.payment;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.ngbilling.core.common.util.FormatLogger;
import com.ngbilling.core.server.persistence.dto.invoice.InvoiceDTO;

/**
 * @author abimael
 */
@Entity
@Table(name = "payment_invoice")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PaymentInvoiceMapDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private static final FormatLogger log = new FormatLogger(PaymentInvoiceMapDTO.class);

    private int id;
    private PaymentDTO payment;
    private InvoiceDTO invoiceEntity;
    private BigDecimal amount;
    private Date createDatetime;
    private int versionNum;

    public PaymentInvoiceMapDTO(Integer id2, BigDecimal amount2, Date create) {
        this.id = id2;
        this.amount = amount2;
        this.createDatetime = create;
    }

    public PaymentInvoiceMapDTO() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_invoice_GEN")
    @SequenceGenerator(
            name = "payment_invoice_GEN",
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
    @JoinColumn(name = "payment_id")
    public PaymentDTO getPayment() {
        return payment;
    }

    public void setPayment(PaymentDTO payment) {
        this.payment = payment;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id")
    public InvoiceDTO getInvoiceEntity() {
        return invoiceEntity;
    }

    public void setInvoiceEntity(InvoiceDTO invoiceId) {
        this.invoiceEntity = invoiceId;
    }

    @Column(name = "amount", precision = 17, scale = 17)
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Column(name = "create_datetime", nullable = false, length = 29)
    public Date getCreateDatetime() {
        return this.createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    @Version
    @Column(name = "OPTLOCK")
    public int getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(int versionNum) {
        this.versionNum = versionNum;
    }

}
