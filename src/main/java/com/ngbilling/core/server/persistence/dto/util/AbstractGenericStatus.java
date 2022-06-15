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

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Abstract class for status classes. The get/setId() methods maps to
 * the status_value, instead of the primary key. Allows use of status
 * constants as the id.
 */
@Entity
@Table(name = "generic_status")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "dtype",
        discriminatorType = DiscriminatorType.STRING
)
public abstract class AbstractGenericStatus extends AbstractDescription {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    protected int id;
    protected Integer statusValue;
    protected Integer order;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genericStatus_GEN")
    @SequenceGenerator(
            name = "genericStatus_GEN",
            allocationSize = 1
    )

    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "ordr")
    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    @Column(name = "status_value", unique = true, nullable = false)
    public Integer getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(Integer statusValue) {
        this.statusValue = statusValue;
    }
}
