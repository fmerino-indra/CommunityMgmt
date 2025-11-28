package org.fmm.communitymgmt.calendar.rules;

import org.fmm.communitymgmt.service.rules.HolidayService;

public class RuleContext {

    private HolidayService holidayService;

//    public RuleContext(HolidayService holidayService) {
//        this.holidayService = holidayService;
//    }

    public HolidayService getHolidayService() {
        return holidayService;
    }
}
