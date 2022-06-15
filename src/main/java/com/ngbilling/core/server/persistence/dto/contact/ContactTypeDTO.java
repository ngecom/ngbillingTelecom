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
package com.ngbilling.core.server.persistence.dto.contact;


import java.util.HashSet;
import java.util.Set;

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
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.ngbilling.core.server.persistence.dto.user.CompanyDTO;
import com.ngbilling.core.server.persistence.dto.util.AbstractDescription;
import com.ngbilling.core.server.util.ServerConstants;

@Entity
@Table(name = "contact_type")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ContactTypeDTO extends AbstractDescription implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int id;
    private CompanyDTO entity;
    private Integer isPrimary;
    private Set<ContactMapDTO> contactMaps = new HashSet<ContactMapDTO>(0);
    private Integer versionNum;

    public ContactTypeDTO() {
    }

    public ContactTypeDTO(int id) {
        this.id = id;
    }

    public ContactTypeDTO(int id, CompanyDTO entity, Integer isPrimary, Set<ContactMapDTO> contactMaps) {
        this.id = id;
        this.entity = entity;
        this.isPrimary = isPrimary;
        this.contactMaps = contactMaps;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contact_type_GEN")
    @SequenceGenerator(
            name = "contact_type_GEN",
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
    @JoinColumn(name = "entity_id")
    public CompanyDTO getEntity() {
        return this.entity;
    }

    public void setEntity(CompanyDTO entity) {
        this.entity = entity;
    }

    @Column(name = "is_primary")
    public Integer getIsPrimary() {
        return this.isPrimary;
    }

    public void setIsPrimary(Integer isPrimary) {
        this.isPrimary = isPrimary;
    }

    public void setContactMaps(Set<ContactMapDTO> contactMaps) {
        this.contactMaps = contactMaps;
    }

    @Version
    @Column(name = "OPTLOCK")
    public Integer getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(Integer versionNum) {
        this.versionNum = versionNum;
    }

    @Transient
    protected String getTable() {
        return ServerConstants.TABLE_CONTACT_TYPE;
    }

    @Override
    public String toString() {
        return "ContactTypeDTO{"
                + "id=" + id
                + ", entityId=" + (entity != null ? entity.getId() : null)
                + ", isPrimary=" + isPrimary
                + '}';
    }
}


