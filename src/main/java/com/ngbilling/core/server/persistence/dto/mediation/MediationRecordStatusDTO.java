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

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.ngbilling.core.server.persistence.dto.util.AbstractGenericStatus;
import com.ngbilling.core.server.util.ServerConstants;

@Entity
@DiscriminatorValue("mediation_record_status")
public class MediationRecordStatusDTO extends AbstractGenericStatus implements java.io.Serializable {


    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public MediationRecordStatusDTO() {
    }


    public MediationRecordStatusDTO(int statusValue) {
        this.statusValue = statusValue;
    }

    @Transient
    protected String getTable() {
        return ServerConstants.TABLE_MEDIATION_RECORD_STATUS;
    }

}
