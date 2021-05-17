/*
 * JBILLING CONFIDENTIAL
 * _____________________
 *
 * [2003] - [2012] Enterprise jBilling Software Ltd.
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
package com.ngbilling.core.server.persistence.dto.mediation;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "mediation_process")
// no cache. This table is not read repeatedly
public class MediationProcess implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "mediation_process_id")
    public Collection<MediationOrderMap> orderMap = new ArrayList<MediationOrderMap>(0);
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "process")
    public Collection<MediationRecordDTO> records = new ArrayList<MediationRecordDTO>(0);
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mediation_process_GEN")
    @SequenceGenerator(
            name = "mediation_process_GEN",
            allocationSize = 1
    )
    private Integer id;
    @Column(name = "start_datetime")
    private Date startDatetime;
    @Column(name = "end_datetime")
    private Date endDatetime;
    @Column(name = "orders_affected")
    private Integer ordersAffected;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "configuration_id")
    private MediationConfiguration configuration;
    @Version
    @Column(name = "OPTLOCK")
    private Integer versionNum;

    public Date getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(Date endDatetime) {
        this.endDatetime = endDatetime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrdersAffected() {
        return ordersAffected;
    }

    public void setOrdersAffected(Integer ordersAffected) {
        this.ordersAffected = ordersAffected;
    }

    public Date getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(Date startDatetime) {
        this.startDatetime = startDatetime;
    }

    public Integer getVersionNum() {
        return versionNum;
    }

    public Collection<MediationOrderMap> getOrderMap() {
        return orderMap;
    }

    public void setOrderMap(Collection<MediationOrderMap> orderMap) {
        this.orderMap = orderMap;
    }

    public MediationConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(MediationConfiguration configuration) {
        this.configuration = configuration;
    }

    public Collection<MediationRecordDTO> getRecords() {
        return records;
    }

    public void setRecords(Collection<MediationRecordDTO> records) {
        this.records = records;
    }

    public String toString() {
        return "MediationProcess= " +
                " orders affected = " + getOrdersAffected();
    }
}
