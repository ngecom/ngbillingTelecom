package com.ngbilling.core.payload.request.process;

import org.joda.time.Days;
import org.joda.time.LocalDate;

import java.io.Serializable;
import java.util.Date;

public class PeriodOfTime implements Serializable {

    static public final PeriodOfTime OneTimeOrderPeriodOfTime = new PeriodOfTime() {
        @Override
        public Date getStart() {
            return null;
        }

        @Override
        public Date getEnd() {
            return null;
        }

        @Override
        public int getDaysInPeriod() {
            return 0;
        }
    };
    private final LocalDate start;
    private final LocalDate end;
    private final int daysInCycle;

    public PeriodOfTime(Date start, Date end, int dayInCycle) {
        this.start = new LocalDate(start.getTime());
        this.end = new LocalDate(end.getTime());
        this.daysInCycle = dayInCycle;
    }

    private PeriodOfTime() {
        this.start = null;
        this.end = null;
        this.daysInCycle = 0;
    }

    public Date getEnd() {
        return end.toDate();
    }

    public LocalDate getDateMidnightEnd() {
        return end;
    }

    public Date getStart() {
        return start.toDate();
    }

    public LocalDate getDateMidnightStart() {
        return start;
    }

    public int getDaysInCycle() {
        return daysInCycle;
    }

    /**
     * Find the number of days between the period start date to the period end date. This means
     * that the start date is counted as a days within the period, but not the end date. For example, January 01 to
     * January 10th includes 9 days total.
     * <p>
     * This method takes into account daylight savings time to ensure that days are counted
     * correctly across DST boundaries.
     *
     * @return number of days between start and end dates
     */
    public int getDaysInPeriod() {
        if (end.isBefore(start) || end.isEqual(start)) {
            return 0;
        }
        return Days.daysBetween(start, end).getDays();
    }

    @Override
    public String toString() {
        return "period starts: " + start + " ends " + end + " days in cycle " + getDaysInCycle();
    }
}
