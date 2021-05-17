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

import com.ngbilling.core.payload.request.configuration.MediationConfigurationWS;
import com.ngbilling.core.server.persistence.dto.pluggableTask.PluggableTaskDTO;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "mediation_cfg")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MediationConfiguration implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "entity_id")
    private Integer entityId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pluggable_task_id")
    private PluggableTaskDTO pluggableTask;

    @Column(name = "name")
    private String name;

    @Column(name = "order_value")
    private Integer orderValue;

    @Column(name = "create_datetime")
    private Date createDatetime;

    @Version
    @Column(name = "OPTLOCK")
    private Integer versionNum;


    public MediationConfiguration() {
    }

    public MediationConfiguration(MediationConfigurationWS ws, PluggableTaskDTO pluggableTask) {
        this.id = ws.getId();
        this.entityId = ws.getEntityId();
        this.pluggableTask = pluggableTask;
        this.name = ws.getName();
        this.orderValue = ws.getOrderValue();
        this.createDatetime = ws.getCreateDatetime();
        this.versionNum = ws.getVersionNum();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }


    public PluggableTaskDTO getPluggableTask() {
        return pluggableTask;
    }

    public void setPluggableTask(PluggableTaskDTO pluggableTask) {
        this.pluggableTask = pluggableTask;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(Integer orderValue) {
        this.orderValue = orderValue;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Integer getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(Integer versionNum) {
        this.versionNum = versionNum;
    }

    public String toString() {
        return "ID: " + id + " name: " + name + " order value: " + orderValue +
                " task: " + pluggableTask + " date: " + createDatetime +
                " entity id: " + entityId;
    }
}
