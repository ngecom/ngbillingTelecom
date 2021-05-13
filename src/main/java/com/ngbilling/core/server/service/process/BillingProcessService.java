package com.ngbilling.core.server.service.process;

import com.ngbilling.core.server.persistence.dto.process.BillingProcessDTO;

import java.util.Date;

public interface BillingProcessService {

    public Date getEndOfProcessPeriod(BillingProcessDTO process) throws Exception;

}