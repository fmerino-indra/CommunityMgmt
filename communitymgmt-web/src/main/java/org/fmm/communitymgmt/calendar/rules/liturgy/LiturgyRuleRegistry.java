package org.fmm.communitymgmt.calendar.rules.liturgy;

import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.fmm.communitymgmt.calendar.rules.RuleKindEnum;
import org.fmm.communitymgmt.calendar.rules.liturgy.computus.Period;
import org.fmm.communitymgmt.calendar.rules.liturgy.result.AbstractLiturgyResult;
import org.fmm.communitymgmt.calendar.rules.liturgy.result.LiturgyDateResult;
import org.fmm.communitymgmt.calendar.rules.liturgy.result.LiturgyPeriodResult;
import org.fmm.communitymgmt.common.util.DateUtil;

public class LiturgyRuleRegistry {
	private final Map<String, LiturgyRule> feastRules = new HashMap<>();
	private final Map<String, LocalDate> computedFeastRules = new HashMap<>();

	private final Map<String, PeriodRule> periodRules = new HashMap<>();
	private final Map<String, LiturgyPeriodResult> computedPeriodRules = new HashMap<>();
	
	private List<LiturgicalFeastDto> liturgicalFeastDtoList = new ArrayList<>();
	private List<PeriodDto> liturgicalPeriodDtoList = new ArrayList<>();
	
	public Map<LocalDate, Set<String>> periodAndFeastIndex() {
		Map<LocalDate, Set<String>> index = new HashMap<>();
		
		for (Map.Entry<String, LocalDate> e : computedFeastRules.entrySet()) {
			index.computeIfAbsent(e.getValue(), d -> new HashSet<>())
			.add(e.getKey());
		}

		LocalDate d = null; 
		Period p = null;
		
		for (Map.Entry<String, LiturgyPeriodResult> e : computedPeriodRules.entrySet()) {
			p = e.getValue().getResult();
			d = p.getInitDate();
			
			while (!d.isAfter(p.getEndDate())) {
				index.computeIfAbsent(d, x -> new HashSet<>())
				.add(e.getKey());
				
				d=d.plusDays(1);
			}
		}
		return index;
	}
	
	public List<PeriodDto> getLiturgicalPeriodDtoList() {
		return liturgicalPeriodDtoList;
	}

	public List<LiturgicalFeastDto> getLiturgicalFeastDtoList() {
		return liturgicalFeastDtoList;
	}

	public void register(LiturgyRule r) {
		feastRules.put(r.getId(), r);
	}
	
	public LiturgyRule getFestRule(String id) {
		return feastRules.get(id);
	}
	
	public Collection<LiturgyRule> allFeastRules() {
		return feastRules.values();
	}
	

	public void registerPeriodRule(PeriodRule r) {
		periodRules.put(r.getId(), r);
	}
	
	public PeriodRule getPeriodRule(String id) {
		return periodRules.get(id);
	}
	
	public Collection<PeriodRule> allPeriodRules() {
		return periodRules.values();
	}
	

	public void setComputedFeastRule(String id, LocalDate d) {
		computedFeastRules.put(id, d);
	}
	public LocalDate getComputedFeastRule(String id) {
		return computedFeastRules.get(id);
	}
	public boolean hasComputedFeastRule(String id) {
		return computedFeastRules.containsKey(id);
	}

	public void setComputedPeriodRule(String id, LiturgyPeriodResult d) {
		computedPeriodRules.put(id, d);
	}
	public LiturgyPeriodResult getComputedPeriodRule(String id) {
		return computedPeriodRules.get(id);
	}
	public boolean hasComputedPeriodRule(String id) {
		return computedPeriodRules.containsKey(id);
	}
	
	public List<LiturgicalFeastDto> evaluateFeastRules(int liturgicalYear, String language, String bishopsConferenceCountry) {
		// compute liturgical year start: first Sunday of Advent of previous calendar
		// year
		LocalDate advStart = DateUtil.computeFirstSundayOfAdvent(liturgicalYear - 1);
		LiturgyRuleContext ctx = new LiturgyRuleContext(liturgicalYear, advStart,Locale.of(language, bishopsConferenceCountry));

		// Build dependency graph based on referencedRuleIds
		Map<String, Set<String>> deps = new HashMap<>(); // node -> set of bases it depends on
		for (AbstractLiturgyRule r : this.allFeastRules())
			deps.put(r.getId(), new HashSet<>(r.getComputus().referencedRuleIds()));

		// Topological sort
		List<String> order = topoSort(deps);

		// Evaluate in order
		//List<LiturgicalFeastDto> out = new ArrayList<>();
		
		for (String ruleId : order) {
			System.out.println();
			AbstractLiturgyRule rule = this.getFestRule(ruleId);
			if (!ruleApplies(rule, ctx))
				continue;
			System.out.printf("Regla(feast): %s -> %s (%s)", rule.id, rule.name, rule.scope);
			AbstractLiturgyResult<?> result = rule.getComputus().compute(liturgicalYear+rule.getLiturgicalYearShift(), ctx, this);
			
			if (rule.getKind() == RuleKindEnum.LITURGY) {
				LiturgyDateResult litDateRes = (LiturgyDateResult)result;
				if (rule.override != null)
					this.setComputedFeastRule(rule.override, litDateRes.getResult());
				else
					this.setComputedFeastRule(ruleId, litDateRes.getResult());
				
				liturgicalFeastDtoList.add(new LiturgicalFeastDto(rule.getId(), rule.getName(), (LocalDate)result.getResult(), rule.getId()));
			}
		}

		// Sort by date for convenience
		// Funcionan las tres
		liturgicalFeastDtoList.sort(Comparator.comparing(f -> f.getDate()));
//		out.sort(Comparator.comparing((LiturgicalFeast f) -> f.getDate()));
//		out.sort(Comparator.comparing(LiturgicalFeast::getDate));
		return liturgicalFeastDtoList;
	}
	public List<PeriodDto> evaluatePeriodRules(int liturgicalYear, String language, String bishopsConferenceCountry) {
		LocalDate advStart = DateUtil.computeFirstSundayOfAdvent(liturgicalYear - 1);
		LiturgyRuleContext ctx = new LiturgyRuleContext(liturgicalYear, advStart,Locale.of(language, bishopsConferenceCountry));
		
		for (PeriodRule periodRule: this.allPeriodRules()) {

			if (!ruleApplies(periodRule, ctx))
				continue;
//			periodRule.getComputus().compute(liturgicalYear,ctx, registry);

			System.out.printf("Rule(period): %s -> %s (%s)\n", periodRule.id, periodRule.name, periodRule.getComputus());
			LiturgyPeriodResult result = (LiturgyPeriodResult)periodRule.getComputus()
					.compute(liturgicalYear+periodRule.getLiturgicalYearShift(), ctx, this);
			
//			if (periodRule.getKind() == RuleKindEnum.LITURGICAL_PERIOD) {
				this.setComputedPeriodRule(periodRule.id, result);
				liturgicalPeriodDtoList.add(new PeriodDto(
						periodRule.getId(), 
						periodRule.getName(), 
						(LocalDate)result.getResult().getInitDate(),
						(LocalDate)result.getResult().getEndDate(),
						periodRule.getKind(),
						periodRule.getId()));
//			}
		}
		liturgicalPeriodDtoList.sort(Comparator.comparing(f -> f.getInitDate()));
		return liturgicalPeriodDtoList;
	}

	

	private boolean ruleApplies(AbstractLiturgyRule rule, LiturgyRuleContext ctx) {
		String value = null;
		if (ctx.region != null)
			value = ctx.region.getCountry();
		return rule.getScope() == null || rule.getScope().isInScope(value);
	}


	private static List<String> topoSort(Map<String, Set<String>> deps) {
		// https://www.geeksforgeeks.org/dsa/topological-sorting-indegree-based-solution/
		// Kahn's algorithm
		Map<String, Set<String>> incoming = new HashMap<>();
		Map<String, Set<String>> outgoing = new HashMap<>();
		Set<String> nodes = new HashSet<>(deps.keySet());
		for (String n : nodes) {
			incoming.put(n, new HashSet<>(deps.getOrDefault(n, Collections.emptySet())));
		}

		// compute reverse (who depends on me)
		for (String n : nodes) {
			for (String base : deps.getOrDefault(n, Collections.emptySet())) {
				outgoing.computeIfAbsent(base, k -> new HashSet<>()).add(n);
			}
		}

		Deque<String> q = new ArrayDeque<>();
		for (String n : nodes)
			if (incoming.getOrDefault(n, Collections.emptySet()).isEmpty())
				q.add(n);

		List<String> result = new ArrayList<>();
		while (!q.isEmpty()) {
			String n = q.removeFirst();
			result.add(n);
			for (String m : outgoing.getOrDefault(n, Collections.emptySet())) {
				incoming.get(m).remove(n);
				if (incoming.get(m).isEmpty())
					q.add(m);
			}
		}
		// if cycle exists, remaining nodes have non-empty incoming
		Set<String> remaining = nodes.stream().filter(x -> !result.contains(x)).collect(Collectors.toSet());
		if (!remaining.isEmpty()) {
			// break cycles by appending remaining (best effort) — in practice raise error
			throw new IllegalStateException("Cycle detected in computus dependencies: " + remaining);
		}
		return result;
	}

}
