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

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.OrderBy;
import org.springframework.beans.factory.annotation.Autowired;

import com.ngbilling.core.common.util.FormatLogger;
import com.ngbilling.core.payload.request.process.BillingProcessWS;
import com.ngbilling.core.server.persistence.dto.invoice.InvoiceDTO;
import com.ngbilling.core.server.persistence.dto.order.OrderProcessDTO;
import com.ngbilling.core.server.persistence.dto.user.CompanyDTO;
import com.ngbilling.core.server.service.process.BillingProcessService;

@Entity
@Table(name = "billing_process")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class BillingProcessDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static final FormatLogger LOG = new FormatLogger(BillingProcessDTO.class);
    private int id;
    private PeriodUnitDTO periodUnitDTO;
    private PaperInvoiceBatchDTO paperInvoiceBatch;
    private CompanyDTO entity;
    private Date billingDate;
    private int periodValue;
    private int isReview;
    private int retriesToDo;
    private Set<OrderProcessDTO> orderProcesses = new HashSet<OrderProcessDTO>(
            0);
    private Set<InvoiceDTO> invoices = new HashSet<InvoiceDTO>(0);
    private Set<ProcessRunDTO> processRuns = new HashSet<ProcessRunDTO>(0);
    private Set<BatchProcessInfoDTO> batchInfos = new HashSet<BatchProcessInfoDTO>(0);
    private int versionNum;
    @Autowired
    private BillingProcessService billingProcessService;

    public BillingProcessDTO() {
    }

    public BillingProcessDTO(BillingProcessWS ws) {
        this.id = ws.getId() != null ? ws.getId() : 0;
        this.billingDate = ws.getBillingDate();
        this.periodValue = ws.getPeriodValue() != null ? ws.getPeriodValue() : 0;
        this.isReview = ws.getReview() != null ? ws.getReview() : 0;
        this.retriesToDo = ws.getRetriesToDo() != null ? ws.getRetriesToDo() : 0;

        if (ws.getPeriodUnitId() != null) this.periodUnitDTO = new PeriodUnitDTO(ws.getPeriodUnitId());
        if (ws.getEntityId() != null) this.entity = new CompanyDTO(ws.getEntityId());
    }

    public BillingProcessDTO(int id, PeriodUnitDTO periodUnitDTO,
                             CompanyDTO entity, Date billingDate, int periodValue, int isReview,
                             int retriesToDo) {
        this.id = id;
        this.periodUnitDTO = periodUnitDTO;
        this.entity = entity;
        this.billingDate = billingDate;
        this.periodValue = periodValue;
        this.isReview = isReview;
        this.retriesToDo = retriesToDo;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "billing_process_GEN")
    @SequenceGenerator(
            name = "billing_process_GEN",
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
    @JoinColumn(name = "period_unit_id", nullable = false)
    public PeriodUnitDTO getPeriodUnit() {
        return this.periodUnitDTO;
    }

    public void setPeriodUnit(PeriodUnitDTO periodUnitDTO) {
        this.periodUnitDTO = periodUnitDTO;
    }


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paper_invoice_batch_id")
    public PaperInvoiceBatchDTO getPaperInvoiceBatch() {
        return this.paperInvoiceBatch;
    }

    public void setPaperInvoiceBatch(PaperInvoiceBatchDTO paperInvoiceBatch) {
        this.paperInvoiceBatch = paperInvoiceBatch;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entity_id", nullable = false)
    public CompanyDTO getEntity() {
        return this.entity;
    }

    public void setEntity(CompanyDTO entity) {
        this.entity = entity;
    }

    @Column(name = "billing_date", nullable = false, length = 13)
    public Date getBillingDate() {
        return this.billingDate;
    }

    public void setBillingDate(Date billingDate) {
        this.billingDate = billingDate;
    }

    /**
     * This function looks at period unit and period value and adds the same
     * to billing process date (or start date), subtracts 1 day
     * to arrive at billing period end date.
     * Marking it transient as we are not storing this to db.
     */
    @Transient
    public Date getBillingPeriodEndDate() throws Exception {
        Calendar calendar = Calendar.getInstance();
        // first add the period value in the given period units
        calendar.setTime(billingProcessService.getEndOfProcessPeriod(this));
        // subtract 1 day
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return calendar.getTime();
    }

    @Column(name = "period_value", nullable = false)
    public int getPeriodValue() {
        return this.periodValue;
    }

    public void setPeriodValue(int periodValue) {
        this.periodValue = periodValue;
    }

    @Column(name = "is_review", nullable = false)
    public int getIsReview() {
        return this.isReview;
    }

    public void setIsReview(int isReview) {
        this.isReview = isReview;
    }

    @Column(name = "retries_to_do", nullable = false)
    public int getRetriesToDo() {
        return this.retriesToDo;
    }

    public void setRetriesToDo(int retriesToDo) {
        this.retriesToDo = retriesToDo;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "billingProcess")
    public Set<OrderProcessDTO> getOrderProcesses() {
        return this.orderProcesses;
    }

    public void setOrderProcesses(Set<OrderProcessDTO> orderProcesses) {
        this.orderProcesses = orderProcesses;
    }

    // this is useful for the cascade, but any call to it will be very expensive and even
    // inaccurate. USE InvoiceDAS.findByProcess instead
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "billingProcess")
    @NotFound(action = NotFoundAction.IGNORE)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    public Set<InvoiceDTO> getInvoices() {
        return this.invoices;
    }

    public void setInvoices(Set<InvoiceDTO> invoices) {
        this.invoices = invoices;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "billingProcess")
    @OrderBy(clause = "id")
    public Set<ProcessRunDTO> getProcessRuns() {
        return this.processRuns;
    }

    public void setProcessRuns(Set<ProcessRunDTO> processRuns) {
        this.processRuns = processRuns;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "billingProcess")
    public Set<BatchProcessInfoDTO> getBatchProcesses() {
        return this.batchInfos;
    }

    public void setBatchProcesses(Set<BatchProcessInfoDTO> batchInfos) {
        this.batchInfos = batchInfos;
    }

    @Version
    @Column(name = "OPTLOCK")
    public int getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(int versionNum) {
        this.versionNum = versionNum;
    }

    @Override
    public String toString() {
        StringBuffer ret = new StringBuffer("BillingProcessDTO: id: " + id + " periodUint " + periodUnitDTO + " paperInvoiceBatch "
                + paperInvoiceBatch + " entity " + entity + " billingDate " + billingDate
                + " periodValue " + periodValue + " isReview " + isReview + " retriesToDo " + retriesToDo);
        ret.append(" orderProcesses (count) ").append(orderProcesses.size());
        ret.append(" invoices (count) ").append(invoices.size());//note, cached association
        ret.append(" processRuns ");
        for (ProcessRunDTO run : processRuns) {
            ret.append(run.toString());
        }
        return ret.toString();
    }

}
