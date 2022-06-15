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

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.ngbilling.core.server.persistence.dto.util.AbstractGenericStatus;
import com.ngbilling.core.server.util.ServerConstants;

@Entity
@DiscriminatorValue("process_run_status")
public class ProcessRunStatusDTO extends AbstractGenericStatus implements java.io.Serializable {


    /**
     *
     */
    private static final long serialVersionUID = 1L;


    public ProcessRunStatusDTO() {
    }


    public ProcessRunStatusDTO(int statusValue) {
        this.statusValue = statusValue;
    }

    @Transient
    protected String getTable() {
        return ServerConstants.TABLE_PROCESS_RUN_STATUS;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ProcessRunStatusDTO [getId()=");
        builder.append(getId());
        builder.append(", getDescription()=");
        builder.append(getDescription());
        builder.append(']');
        return builder.toString();
    }

}
