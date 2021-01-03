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


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.ngbilling.core.server.util.ServerConstants;

@Entity
@Table(name="preference_type")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class PreferenceTypeDTO extends AbstractDescription implements java.io.Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
    private String defaultValue;
    private Set<PreferenceDTO> preferences = new HashSet<PreferenceDTO>(0);

    public PreferenceTypeDTO() {
    }

    public PreferenceTypeDTO(int id) {
        this.id = id;
    }

    public PreferenceTypeDTO(int id, String defaultValue, Set<PreferenceDTO> preferences) {
        this.id = id;
        this.defaultValue = defaultValue;
        this.preferences = preferences;
    }

    @Id
    @Column(name="id", unique=true, nullable=false)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="def_value", length=200)
    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="preferenceType")
    public Set<PreferenceDTO> getPreferences() {
        return this.preferences;
    }

    public void setPreferences(Set<PreferenceDTO> preferences) {
        this.preferences = preferences;
    }

    @Transient
    protected String getTable() {
        return ServerConstants.TABLE_PREFERENCE_TYPE;
    }

    @Transient
    public String getInstructions() {
        return getDescription(ServerConstants.LANGUAGE_ENGLISH_ID, "instruction");
    }

    @Transient
    public String getInstructions(Integer languageId) {
        return getDescription(languageId, "instruction");
    }



}


