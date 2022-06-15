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

/*
 * Created on Apr 25, 2003
 *
 */
package com.ngbilling.core.common.util;

import java.util.GregorianCalendar;

import com.ngbilling.core.server.util.ServerConstants;

/**
 * @author emilc
 */
public class MapPeriodToCalendar {
    public static int map(Integer period)
            throws Exception {
        int retValue;

        if (period == null) {
            throw new Exception("Can't map a period that is null");
        }
        if (period.compareTo(ServerConstants.PERIOD_UNIT_DAY) == 0) {
            retValue = GregorianCalendar.DAY_OF_YEAR;
        } else if (period.compareTo(ServerConstants.PERIOD_UNIT_MONTH) == 0) {
            retValue = GregorianCalendar.MONTH;
        } else if (period.compareTo(ServerConstants.PERIOD_UNIT_WEEK) == 0) {
            retValue = GregorianCalendar.WEEK_OF_YEAR;
        } else if (period.compareTo(ServerConstants.PERIOD_UNIT_YEAR) == 0) {
            retValue = GregorianCalendar.YEAR;
        } else { // error !
            throw new Exception("Period not supported:" + period);
        }

        return retValue;
    }

    public static int periodToDays(Integer period) throws Exception {
        int retValue = 0;
        if (period == null) {
            throw new Exception("Can't convert a period that is null");
        }
        if (period.compareTo(ServerConstants.PERIOD_UNIT_DAY) == 0) {
            retValue = 1;
        } else if (period.compareTo(ServerConstants.PERIOD_UNIT_MONTH) == 0) {
            retValue = 31;
        } else if (period.compareTo(ServerConstants.PERIOD_UNIT_WEEK) == 0) {
            retValue = 7;
        } else if (period.compareTo(ServerConstants.PERIOD_UNIT_YEAR) == 0) {
            retValue = 365;
        } else if (period.compareTo(ServerConstants.PERIOD_UNIT_SEMI_MONTHLY) == 0) {
            retValue = 15;
        } else { // error !
            throw new Exception("Period not supported:" + period);
        }

        return retValue;
    }
}
