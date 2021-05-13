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


import com.ngbilling.core.server.persistence.dao.util.InternationalDescriptionDAO;
import com.ngbilling.core.server.persistence.dao.util.JbillingTableDAO;
import com.ngbilling.core.server.util.ServerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public abstract class AbstractDescription implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String description = null;

    abstract public int getId();

    abstract protected String getTable();

      /**
     * Returns the default english description for this entity. This method caches the description
     * in a local variable so that it can quickly be retrieved on subsequent calls.
     *
     * @return english description string
     */
    public String getDescription() {
        return description;

    }

    /**
     * Sets the cached description to the given text. This does not update the database.
     *
     * @param text description
     */
    public void setDescription(String text) {
        description = text;
    }


}
