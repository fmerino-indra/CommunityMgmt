package org.fmm.communitymgmt.calendar.rules;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class CalendarContext {
    public final LocalDate date;
    public final boolean isHoliday;        // suministrado por servicio de festivos
    public final boolean isHolyWeek;       // calculado por servicio litúrgico
    public final boolean isPalmSunday;     // calculado por servicio litúrgico
    public final DayOfWeek communityWordDay; // día configurado por la comunidad (TUE/WED/THU)
    public final Integer communityConvivenciaOrdinal; // 1..5 (domingo ordinal)

    public CalendarContext(LocalDate date,
                           boolean isHoliday,
                           boolean isHolyWeek,
                           boolean isPalmSunday,
                           DayOfWeek communityWordDay,
                           Integer communityConvivenciaOrdinal) {
        this.date = date;
        this.isHoliday = isHoliday;
        this.isHolyWeek = isHolyWeek;
        this.isPalmSunday = isPalmSunday;
        this.communityWordDay = communityWordDay;
        this.communityConvivenciaOrdinal = communityConvivenciaOrdinal;
    }
}
