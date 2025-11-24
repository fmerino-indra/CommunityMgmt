package org.fmm.communitymgmt.calendar.rules.conditions;

import java.time.DayOfWeek;

import org.fmm.communitymgmt.calendar.rules.CalendarContext;

public class CommunityWeekdayMatchCondition implements Condition {
    @Override
    public boolean evaluate(CalendarContext ctx) {
        DayOfWeek communityDay = ctx.communityWordDay;
        if (communityDay == null) return false;
        return ctx.date.getDayOfWeek() == communityDay;
    }
}
