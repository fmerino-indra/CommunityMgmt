package org.fmm.communitymgmt.calendar.rules;

import java.time.LocalDate;
import java.time.temporal.WeekFields;

public class RuleUtil {
	/**
	 * Calculate the week of month:
	 * Week 1: day 1 - 7
	 * Week 2: day 8 - 14
	 * Week 3: day 15 - 21
	 * Week 4: day 22 - 28
	 * Week 5: day 29 - 31
	 *  
	 * @param date
	 * @return week of month
	 */
	public static int weekOfMonthOfDate(LocalDate date) {
        WeekFields wf = WeekFields.ISO; // lunes como primer día
        int semanaDelMes = date.get(wf.weekOfMonth());
//        System.out.println(semanaDelMes);
		return semanaDelMes;
	}
	
	/**
	 * easter date
	 * @param year
	 * @return easter date in LocalDate format
	 */
	public static LocalDate easterSunday(int year) {
		int a = year % 19;
		int b = year / 100;
		int c = year % 100;
		int d = b / 4;
		int e = b % 4;
		int f = (b + 8) / 25;
		int g = (b - f + 1) / 3;
		int h = (19 * a + b - d - g + 15) % 30;
		int i = c / 4;
		int k = c % 4;
		int l = (32 + 2 * e + 2 * i - h - k) % 7;
		int m = (a + 11 * h + 22 * l) / 451;
		int month = (h + l - 7 * m + 114) / 31;
		int day = ((h + l - 7 * m + 114) % 31) + 1;
		return LocalDate.of(year, month, day);
	}
	
	// -----------------------------
	// Helper: map liturgical year -> calendar year for fixed month
	// If month >= Nov (11) then it belongs to previous calendar year
	// -----------------------------
	/**
	 * Esto no es correcto, ya que el CristoRey puede caer, de hecho suele caer en noviembre
	 * @param liturgicalYear
	 * @param month
	 * @return
	 */
	public static int calendarYearForMonth(int liturgicalYear, int month) {
		if (month>=11)
			return liturgicalYear - 1;
		return liturgicalYear;
	}
}
