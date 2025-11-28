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
	public static int weekOfDate(LocalDate date) {
        WeekFields wf = WeekFields.ISO; // lunes como primer día
        int semanaDelMes = date.get(wf.weekOfMonth());
        System.out.println(semanaDelMes);
		return semanaDelMes;
	}
}
