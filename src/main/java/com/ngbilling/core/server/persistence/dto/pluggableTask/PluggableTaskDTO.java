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

package com.ngbilling.core.server.persistence.dto.pluggableTask;

import java.util.Collection;
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
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.beans.factory.annotation.Autowired;

import com.ngbilling.core.common.util.FormatLogger;
import com.ngbilling.core.payload.request.configuration.PluggableTaskWS;
import com.ngbilling.core.server.persistence.dao.pluggableTask.PluggableTaskDAO;
import com.ngbilling.core.server.persistence.dao.pluggableTask.PluggableTaskTypeDAO;


@Entity
@Table(name = "pluggable_task")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PluggableTaskDTO implements java.io.Serializable {

    //  this is in synch with the DB (pluggable task type)
    public static final Integer TYPE_EMAIL = 9;
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static final FormatLogger LOG = new FormatLogger(PluggableTaskDTO.class);
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "entity_id")
    private Integer entityId;

    @Column(name = "processing_order")
    private Integer processingOrder;

    @Column(name = "notes")
    private String notes;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private PluggableTaskTypeDTO type;

    @OneToMany(mappedBy = "task", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<PluggableTaskParameterDTO> parameters;

    @Version
    @Column(name = "OPTLOCK")
    private Integer versionNum;

    @Transient
    @Autowired
    private PluggableTaskTypeDAO pluggableTaskTypeDAO;

    @Transient
    @Autowired
    private PluggableTaskDAO pluggableTaskDAO;

    public PluggableTaskDTO() {
        type = new PluggableTaskTypeDTO();
    }

    public PluggableTaskDTO(Integer entityId, Integer processingOrder, PluggableTaskTypeDTO type) {
        this.entityId = entityId;
        this.processingOrder = processingOrder;
        this.type = type;
    }

    public PluggableTaskDTO(Integer entityId, PluggableTaskWS ws) {
        setEntityId(entityId);
        setId(ws.getId());
        setProcessingOrder(ws.getProcessingOrder());
        setNotes(ws.getNotes());
        setType(pluggableTaskTypeDAO.findById(ws.getTypeId()).get());
        versionNum = ws.getVersionNumber();
        parameters = new HashSet<>();
        // if this is an existing plug-in..
        Collection<PluggableTaskParameterDTO> params = null;
        if (getId() != null && getId() > 0) {
            params = pluggableTaskDAO.findById(getId()).get().getParameters();
        }
        for (String key : ws.getParameters().keySet()) {
            PluggableTaskParameterDTO parameter = new PluggableTaskParameterDTO();
            parameter.setName(key);
            parameter.setStrValue(ws.getParameters().get(key));
            parameter.setTask(this);
            parameters.add(parameter);
            if (params != null) {
                for (PluggableTaskParameterDTO dbParam : params) {
                    if (dbParam.getName().equals(parameter.getName())) {
                        parameter.setId(dbParam.getId());
                        parameter.setVersionNum(dbParam.getVersionNum());
                    }
                }
            }
        }
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProcessingOrder() {
        return processingOrder;
    }

    public void setProcessingOrder(Integer processingOrder) {
        this.processingOrder = processingOrder;
    }

    public Set<PluggableTaskParameterDTO> getParameters() {
        return parameters;
    }

    public void setParameters(Set<PluggableTaskParameterDTO> parameters) {
        this.parameters = parameters;
    }

    public PluggableTaskTypeDTO getType() {
        return type;
    }

    public void setType(PluggableTaskTypeDTO type) {
        this.type = type;
    }

    public Integer getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(Integer versionNum) {
        this.versionNum = versionNum;
    }

    public void populateParamValues() {
        if (parameters != null) {
            for (PluggableTaskParameterDTO parameter : parameters) {
                parameter.populateValue();
            }
        }
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PluggableTaskDTO{");
        sb.append("id=").append(id);
        sb.append(", entityId=").append(entityId);
        sb.append(", processingOrder=").append(processingOrder);
        sb.append(", notes='").append(notes).append('\'');
        sb.append(", type=").append(type);
        sb.append(", parameters=").append(parameters);
        sb.append(", versionNum=").append(versionNum);
        sb.append('}');
        return sb.toString();
    }

    public void touch() {
        if (null != getType()) {
            getType().touch();
        }

        if (null != getParameters()) {
            for (PluggableTaskParameterDTO param : getParameters()) {
                param.getValue();
            }
        }
    }

}
