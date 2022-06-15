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
package com.ngbilling.core.server.persistence.dto.audit;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.ngbilling.core.server.persistence.dto.util.AbstractDescription;
import com.ngbilling.core.server.util.ServerConstants;

@Entity
@Table(name = "event_log_message")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class EventLogMessageDTO extends AbstractDescription {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "eventLogMessage")
    private Set<EventLogDTO> eventLogs = new HashSet<EventLogDTO>(0);

    protected EventLogMessageDTO() {
    }

    public EventLogMessageDTO(int id) {
        this.id = id;
    }

    public EventLogMessageDTO(int id, Set<EventLogDTO> eventLogs) {
        this.id = id;
        this.eventLogs = eventLogs;
    }

    @Override
    protected String getTable() {
        return ServerConstants.TABLE_EVENT_LOG_MESSAGE;
    }

    public int getId() {
        return this.id;
    }

    public Set<EventLogDTO> getEventLogs() {
        return this.eventLogs;
    }

}


