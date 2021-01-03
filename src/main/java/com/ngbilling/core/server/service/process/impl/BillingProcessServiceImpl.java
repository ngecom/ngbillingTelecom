package com.ngbilling.core.server.service.process.impl;

import java.util.Date;
import java.util.GregorianCalendar;

import com.ngbilling.core.common.util.CalendarUtils;
import com.ngbilling.core.common.util.MapPeriodToCalendar;
import com.ngbilling.core.server.persistence.dto.process.BillingProcessDTO;
import com.ngbilling.core.server.service.process.BillingProcessService;

public class BillingProcessServiceImpl implements BillingProcessService{

	@Override
	public Date getEndOfProcessPeriod(BillingProcessDTO process) throws Exception {
	        GregorianCalendar cal = new GregorianCalendar();

	        cal.setTime(process.getBillingDate());
	        
	        if (CalendarUtils.isSemiMonthlyPeriod(process.getPeriodUnit())) {
	        	cal.setTime(CalendarUtils.addSemiMonthyPeriod(cal.getTime()));
	        } else {
	        	cal.add(MapPeriodToCalendar.map(process.getPeriodUnit().getId()),
	        			process.getPeriodValue());
	        }

	        return cal.getTime();
	    }
}
