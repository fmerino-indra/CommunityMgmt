package org.fmm.communitymgmt.service.planning;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.fmm.communitymgmt.calendar.rules.RulesEngine;
import org.fmm.communitymgmt.calendar.rules.effect.CancelEffect;
import org.fmm.communitymgmt.calendar.rules.effect.RuleEffect;
import org.fmm.communitymgmt.common.model.Community;
import org.fmm.communitymgmt.common.model.CommunitySettings;
import org.fmm.communitymgmt.common.model.calendar.Event;
import org.fmm.communitymgmt.common.model.common.TEventType;
import org.fmm.communitymgmt.common.model.common.TTripod;
import org.fmm.communitymgmt.common.model.templates.CelebrationEventTemplate;
import org.fmm.communitymgmt.common.repository.CommunitySettingsRepository;
import org.fmm.communitymgmt.common.repository.calendar.EventRepository;
import org.fmm.communitymgmt.common.repository.templates.CelebrationEventTemplateRepository;
import org.fmm.communitymgmt.common.util.enums.EventTypeEnum;
import org.fmm.communitymgmt.common.util.enums.TripodEnum;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.annotation.PostConstruct;

@org.springframework.stereotype.Service("CommunityPlanningService")
public class CommunityPlanningServiceImpl implements CommunityPlanningService {

    @Autowired
    private CommunitySettingsRepository communitySettingsRepository;
    
    @Autowired
    private EventRepository eventRepository;
/*
    @Autowired
    private TEventTypeRepository tEventTypeRepository;
    
    @Autowired
    private CelebrationCycleTemplateRepository cctRepository;
*/  
    @Autowired
    private CelebrationEventTemplateRepository cetRepository;
    
    @Autowired
    private RulesEngine rulesEngine;
    
    @PostConstruct
    private void init() {
//        random = new Random(System.currentTimeMillis());
    }

	@Override
	public void preparePlanning(LocalDate fromLDT, LocalDate toLDT, Community community) {
	}
	
	@Override
	public void planning(LocalDate fromLDT, LocalDate toLDT, Community community) {
		CommunitySettings settings = null;
		Optional<CommunitySettings> ocs = communitySettingsRepository.findById(community.getId());
		if (ocs.isPresent()) {
			settings = ocs.get();
		}
		if (settings == null) {
			return;
		}
		planningConvivence(fromLDT, toLDT, community, settings.getConvivenceWeek(), settings.getConvivenceTime());
		planningEucharists(fromLDT, toLDT, community, settings.getEucharistTime());
		planningWords(fromLDT, toLDT, community,DayOfWeek.of(settings.getWordDay()) ,settings.getWordTime(), 1);
		
	}

	/* 
	 * @TODO ¿Incluir dirección en los eventos convivencias?
	 */
	private void planningConvivence(LocalDate fromLDT, LocalDate toLDT, Community community, int nDay, LocalTime time) {
		//ChronoUnit.WEEKS.
		//Period
		//TemporalAdjusters.
		//WeekFields
		
		
		List<LocalDate> convivenceDates = getNDayOfWeekByMonth(fromLDT, toLDT, DayOfWeek.SUNDAY, nDay);
		//tEventTypeRepository.findAll();
		Event convivence = null;
		RuleEffect effect = null;
		for (LocalDate date: convivenceDates) {
			
			effect = rulesEngine.evaluate(TripodEnum.COMMUNITY, date);
			if (effect instanceof CancelEffect)
				continue;

			convivence = new Event();
			convivence.setEventDate(date);
			convivence.setEventTime(time);
			convivence.setEventName(String.format("Convivence %s",date.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault())));
			convivence.setCommunity(community);
			convivence.setNeedGroup(false);
			convivence.setTripodType(TTripod.from(TripodEnum.COMMUNITY));
			convivence.setEventLocation(TEventType.from(EventTypeEnum.OTHER));
			eventRepository.save(convivence);
		}
	}
	
	private void planningEucharists(LocalDate fromLDT, LocalDate toLDT, Community community, LocalTime time) {
		//ChronoUnit.WEEKS.
		//Period
		//TemporalAdjusters.
		//WeekFields
		
		
		List<LocalDate> eucharistDates = getDaysOfWeek(fromLDT, toLDT, DayOfWeek.SATURDAY);
		//tEventTypeRepository.findAll();
		Event convivence = null;
		for (LocalDate date: eucharistDates) {
			convivence = new Event();
			convivence.setEventDate(date);
			convivence.setEventTime(time);
			convivence.setEventName(String.format("Eucharist %s",date.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault())));
			convivence.setCommunity(community);
			convivence.setNeedGroup(true);
			convivence.setTripodType(TTripod.from(TripodEnum.LITURGY));
			convivence.setEventLocation(TEventType.from(EventTypeEnum.INPARISH));
			eventRepository.save(convivence);
		}
	}
	
	/**
	 * Plan Words Cycles between two dates.
	 * Validates if the number of days of the cycle in settings is upper than number of free days (Exception) 
	 * @param fromLDT
	 * @param toLDT
	 * @param community
	 * @param time
	 * @param tolerance. Número de eventos de la plantilla que no caben en el último ciclo. Si la tolerancia es menor que el resto (mod) el último ciclo no se genera.
	 */
	private void planningWords(LocalDate fromLDT, LocalDate toLDT, Community community, DayOfWeek day, LocalTime time, int tolerance) {
		//ChronoUnit.WEEKS.
		//Period
		//TemporalAdjusters.
		//WeekFields
		List<CelebrationEventTemplate> eventTemplates = null;
		List<LocalDate> wordDates = null;
		int templateNDays = 0;
		int periodNDays = 0;
		int cycles = 0;
		int rest = 0;
		
		eventTemplates = cetRepository.listEventTemplatesByCycleId(1);
		templateNDays = eventTemplates.size();
		
		
				
		wordDates= getDaysOfWeek(fromLDT, toLDT, day);
		periodNDays = wordDates.size();
		
		cycles = periodNDays / templateNDays;
		rest = periodNDays % templateNDays;
		Event wordCelebration = null;
		LocalDate date = null;
		int acumWeeks = 0;
		// Recorremos los ciclos
		for (int cycle = 0; cycle < cycles; cycle++) {
			date = wordDates.get(cycle*templateNDays);
			acumWeeks=0;
			// Recorremos un ciclo
			for (CelebrationEventTemplate cet:eventTemplates) {
				wordCelebration = new Event();
				wordCelebration.setEventDate(date.plusWeeks(acumWeeks));
				wordCelebration.setEventTime(time);
				//wordCelebration.setEventName(String.format("Word %s",wordDates.get(cycle).getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault())));
				wordCelebration.setEventName(cet.getName());
				wordCelebration.setCommunity(community);
				wordCelebration.setNeedGroup(true);
				wordCelebration.setTripodType(TTripod.from(TripodEnum.WORD)); 
				//wordCelebration.setEventLocation(TEventType.from(EventTypeEnum.INPARISH));
				wordCelebration.setEventLocation(cet.getEventLocation());
				eventRepository.save(wordCelebration);
				acumWeeks++;
			}
		}
		
		// 
		if (tolerance >= rest) {
			
		}
		
		for (LocalDate date2: wordDates) {
		}
	}
	
	/**
	 * 
	 * @param from
	 * @param until
	 * @param dayOfWeek
	 * @param n is the n-sime dayOfWeek in the month. e.g. 3rd sunday
	 * @return
	 */
	private static List<LocalDate> getNDayOfWeekByMonth(LocalDate from, LocalDate until, DayOfWeek dayOfWeek, int n) {
		List<LocalDate> dates = new ArrayList<LocalDate>();
		
		LocalDate date = null;
		LocalDate init = null;
		
		init = from.withDayOfMonth(1);
		while (!init.isAfter(until)) {
			// 3º domingo de mes, p.ej.
			// n dayOfWeek of month
			date = init.with(TemporalAdjusters.dayOfWeekInMonth(n, dayOfWeek));
			if (!date.isBefore(from) && !date.isAfter(until))
				dates.add(date);
			init = init.plusMonths(1).withDayOfMonth(1);
		}
		return dates;
	}

	/**
	 * Returns a list with all dayOfWeek dates. e.g. All Saturdays between two dates
	 * @param from
	 * @param until
	 * @param dayOfWeek
	 * @return
	 */
	private static List<LocalDate> getDaysOfWeek(LocalDate from, LocalDate until, DayOfWeek dayOfWeek) {
		List<LocalDate> dates = new ArrayList<LocalDate>();
		
		LocalDate date = null;
		LocalDate init = null;
		
		init = from;
		while (!init.isAfter(until)) {
			date = init.with(TemporalAdjusters.nextOrSame(dayOfWeek));
			if (!date.isBefore(from) && !date.isAfter(until))
				dates.add(date);
			init = init.plusWeeks(1);
		}
		return dates;
	}
	
	public static void main(String [] args) {
		LocalDate date = null;
		LocalDate from = null;
		
		from = LocalDate.of(2025, 4, 23);
		
		int n = -5;
		for (int i = -5;i<6;i++) {
			date = from.with(TemporalAdjusters.dayOfWeekInMonth(i, DayOfWeek.SUNDAY));
			System.out.printf("%d Sunday: %s",i, date);
			System.out.println();
		}
		n = 3;
		DayOfWeek day = DayOfWeek.TUESDAY;
		List<LocalDate> dates = getNDayOfWeekByMonth(LocalDate.of(2025, 2, 15), LocalDate.of(2025, 12, 25), day, n);
		for (LocalDate fecha: dates) {
			System.out.printf("%d %s: %s\n", n, day, fecha);
		}
	}
}
