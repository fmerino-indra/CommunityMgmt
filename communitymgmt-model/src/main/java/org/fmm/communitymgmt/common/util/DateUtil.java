package org.fmm.communitymgmt.common.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DateUtil {
    public static ZonedDateTime localDateToZonedDateTime(LocalDate date, LocalTime time) {
        LocalDateTime aux = date.atTime(time);
        return aux.atZone(ZoneId.systemDefault());
    }
    
    public static ZonedDateTime localDateToZonedDateTime(LocalDate date) {
        LocalTime time = LocalTime.of(0,0);
        return localDateToZonedDateTime(date, time);
    }
    
    @Deprecated
    public static OffsetDateTime localDateToOffsetDateTime(LocalDate date, LocalTime time) {
        LocalDateTime aux = date.atTime(time);
//        service.setServiceDate(Date.from(sunday.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        return aux.atZone(ZoneId.systemDefault()).toOffsetDateTime();
    }
    
    @Deprecated
    public static OffsetDateTime localDateToOffsetDateTime(LocalDate date) {
        LocalTime time = LocalTime.of(0, 0);
        return localDateToOffsetDateTime(date, time);
    }
    /**
     * Returns a list of LocalDate with sundays
     * @param from
     * @param to
     * @return
     */
    public static List<LocalDate> sundaysBetween(LocalDate from, LocalDate to) {
        List<LocalDate> lista = null;
        
        lista = new ArrayList<LocalDate>();
        LocalDate sunday = null;
        DayOfWeek dayOfWeekFrom = null;
        int diffFrom = -1;
        
        sunday = from;
        
        dayOfWeekFrom = sunday.getDayOfWeek();
        // El domingo devuelve 7
        diffFrom = DayOfWeek.SUNDAY.getValue() - dayOfWeekFrom.getValue(); 

        if (diffFrom == 0) {
            lista.add(sunday);
            diffFrom = 7;
        }
        
        do {
            sunday = sunday.plus(diffFrom, ChronoUnit.DAYS);
            lista.add(sunday);
            diffFrom = 7;
        } while (ChronoUnit.DAYS.between(to, sunday) <= -7);
        
        return lista;
    }

    @Deprecated
    public static List<LocalDate> sundaysBetweenOld(LocalDate from, LocalDate to) {
        List<LocalDate> lista = null;
        
        lista = new ArrayList<LocalDate>();
        LocalDate sunday = null;
        int dayOfWeekFrom = -1;
        int diffFrom = -1;
        
        sunday = from;
        
        dayOfWeekFrom = sunday.get(ChronoField.DAY_OF_WEEK);
        diffFrom = DayOfWeek.SUNDAY.getValue() - dayOfWeekFrom; 

        if (diffFrom == 0)
            diffFrom = 7;
        
        do {
            sunday = sunday.plus(diffFrom, ChronoUnit.DAYS);
            lista.add(sunday);
            diffFrom = 7;
        } while (ChronoUnit.DAYS.between(to, sunday) <= -7);
        
        return lista;
    }
    
    public static Date from(int year, int month, int day) {
    	return Date.from(LocalDate.of(year, month, day).atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
    
    public static Long toEpochDays(int year, int month, int day) {
    	return LocalDate.of(year, month, day).toEpochDay();
    }

}
