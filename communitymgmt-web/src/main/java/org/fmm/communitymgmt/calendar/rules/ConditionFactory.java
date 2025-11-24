package org.fmm.communitymgmt.calendar.rules;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import org.fmm.communitymgmt.calendar.rules.conditions.CommunityWeekdayMatchCondition;
import org.fmm.communitymgmt.calendar.rules.conditions.Condition;
import org.fmm.communitymgmt.calendar.rules.conditions.CustomDateCondition;
import org.fmm.communitymgmt.calendar.rules.conditions.DateEqualsMmDdCondition;
import org.fmm.communitymgmt.calendar.rules.conditions.DateRangeMmDdCondition;
import org.fmm.communitymgmt.calendar.rules.conditions.DayOfWeekCondition;
import org.fmm.communitymgmt.calendar.rules.conditions.IsHolidayCondition;
import org.fmm.communitymgmt.calendar.rules.conditions.IsHolyWeekCondition;
import org.fmm.communitymgmt.calendar.rules.conditions.IsPalmSundayCondition;
import org.fmm.communitymgmt.calendar.rules.conditions.MonthInCondition;

import com.fasterxml.jackson.databind.JsonNode;

public class ConditionFactory {

    public static Condition fromJson(JsonNode node) {
        String type = node.get("type").asText();

        switch (type) {
            case "DATE_EQUALS_MMDD":
                return new DateEqualsMmDdCondition(node.get("value").asText());
            case "DATE_RANGE_MMDD":
                return new DateRangeMmDdCondition(node.get("from").asText(), node.get("to").asText());
            case "DAY_OF_WEEK":
                return new DayOfWeekCondition(DayOfWeek.valueOf(node.get("value").asText()));
            case "MONTH_IN":
                List<Integer> months = new ArrayList<>();
                for (JsonNode v : node.get("values")) months.add(v.asInt());
                return new MonthInCondition(months);
            case "IS_HOLIDAY":
                return new IsHolidayCondition();
            case "IS_HOLY_WEEK":
                return new IsHolyWeekCondition();
            case "IS_PALM_SUNDAY":
                return new IsPalmSundayCondition();
            case "COMMUNITY_WEEKDAY_MATCH":
                return new CommunityWeekdayMatchCondition();
            case "CUSTOM_DATE":
                return new CustomDateCondition(node.get("value").asText());
            default:
                throw new IllegalArgumentException("Unknown condition type: " + type);
        }
    }

    public static List<Condition> listFromJson(JsonNode arrayNode) {
        List<Condition> list = new ArrayList<>();
        for (JsonNode n : arrayNode) list.add(fromJson(n));
        return list;
    }
}
