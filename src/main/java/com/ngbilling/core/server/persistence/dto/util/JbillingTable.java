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
package com.ngbilling.core.server.persistence.dto.util;


import com.ngbilling.core.server.persistence.dto.audit.EventLogDTO;
import com.ngbilling.core.server.persistence.dto.contact.ContactMapDTO;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "jbilling_table"
        , uniqueConstraints = @UniqueConstraint(columnNames = "name")
)
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class JbillingTable implements java.io.Serializable {


    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private Set<ContactMapDTO> contactMaps = new HashSet<ContactMapDTO>(0);
    private Set<PreferenceDTO> preferences = new HashSet<PreferenceDTO>(0);
    private Set<EventLogDTO> eventLogs = new HashSet<EventLogDTO>(0);

    public JbillingTable() {
    }


    public JbillingTable(int id, String name, int nextId) {
        this.id = id;
        this.name = name;
    }

    public JbillingTable(int id, String name, int nextId, Set<ContactMapDTO> contactMaps, Set<PreferenceDTO> preferences, Set<EventLogDTO> eventLogs) {
        this.id = id;
        this.name = name;
        this.contactMaps = contactMaps;
        this.preferences = preferences;
        this.eventLogs = eventLogs;
    }

    @Id
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name", unique = true, nullable = false, length = 50)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "jbillingTable")
    public Set<ContactMapDTO> getContactMaps() {
        return this.contactMaps;
    }

    public void setContactMaps(Set<ContactMapDTO> contactMaps) {
        this.contactMaps = contactMaps;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "jbillingTable")
    public Set<PreferenceDTO> getPreferences() {
        return this.preferences;
    }

    public void setPreferences(Set<PreferenceDTO> preferences) {
        this.preferences = preferences;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "jbillingTable")
    public Set<EventLogDTO> getEventLogs() {
        return this.eventLogs;
    }

    public void setEventLogs(Set<EventLogDTO> eventLogs) {
        this.eventLogs = eventLogs;
    }

}


