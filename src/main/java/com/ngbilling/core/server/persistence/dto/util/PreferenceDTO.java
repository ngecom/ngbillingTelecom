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


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "preference")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PreferenceDTO implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int id;
    private JbillingTable jbillingTable;
    private PreferenceTypeDTO preferenceType;
    private int foreignId;
    private String value;

    public PreferenceDTO() {
    }

    public PreferenceDTO(int id, JbillingTable jbillingTable, int foreignId) {
        this.id = id;
        this.jbillingTable = jbillingTable;
        this.foreignId = foreignId;
    }

    public PreferenceDTO(int id, JbillingTable jbillingTable, PreferenceTypeDTO preferenceType, int foreignId, String value) {
        this.id = id;
        this.jbillingTable = jbillingTable;
        this.preferenceType = preferenceType;
        this.foreignId = foreignId;
        this.value = value;
    }

    public PreferenceDTO(JbillingTable jbillingTable, PreferenceTypeDTO preferenceType, int foreignId, String value) {
        this.jbillingTable = jbillingTable;
        this.preferenceType = preferenceType;
        this.foreignId = foreignId;
        this.value = value;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_id", nullable = false)
    public JbillingTable getJbillingTable() {
        return this.jbillingTable;
    }

    public void setJbillingTable(JbillingTable jbillingTable) {
        this.jbillingTable = jbillingTable;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    public PreferenceTypeDTO getPreferenceType() {
        return this.preferenceType;
    }

    public void setPreferenceType(PreferenceTypeDTO preferenceType) {
        this.preferenceType = preferenceType;
    }

    @Column(name = "foreign_id", nullable = false)
    public int getForeignId() {
        return this.foreignId;
    }

    public void setForeignId(int foreignId) {
        this.foreignId = foreignId;
    }

    @Column(name = "value", length = 200)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setValue(Integer value) {
        this.value = (value != null ? value.toString() : null);
    }

    public void setValue(BigDecimal value) {
        this.value = (value != null ? value.toString() : null);
    }

    @Transient
    public Integer getIntValue() {
        return value != null ? Integer.valueOf(value) : null;
    }

    @Transient
    public BigDecimal getFloatValue() {
        return value != null ? new BigDecimal(value) : null;
    }

}


