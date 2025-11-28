package org.fmm.communitymgmt.service.rules;

import java.time.LocalDate;

import org.fmm.communitymgmt.common.util.enums.TripodEnum;
import org.springframework.stereotype.Service;

@Service
public class LiturgyServiceImpl implements LiturgyService {

    @Override
    public LocalDate easterSunday(int year) {
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

    @Override
    public LocalDate palmSunday(int year) {
        return easterSunday(year).minusDays(7);
    }

    @Override
    public LocalDate inAlbis(int year) {
        return easterSunday(year).plusDays(7);
    }

    @Override
    public boolean isHolyWeek(LocalDate date) {
        LocalDate palm = palmSunday(date.getYear());
        LocalDate inAlbis = inAlbis(date.getYear());
        return !date.isBefore(palm) && !date.isAfter(inAlbis);
    }

	@Override
	public boolean isCelebrationCancelled(TripodEnum type, LocalDate date) {
		// TODO Auto-generated method stub
		return false;
	}
}
