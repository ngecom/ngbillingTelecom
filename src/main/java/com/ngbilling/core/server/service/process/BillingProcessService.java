package com.ngbilling.core.server.service.process;

import java.util.Date;

import com.ngbilling.core.server.persistence.dto.process.BillingProcessDTO;

public interface BillingProcessService {

    public Date getEndOfProcessPeriod(BillingProcessDTO process) throws Exception;

}