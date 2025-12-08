package org.fmm.communitymgmt.calendar.rules.liturgy;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class LiturgyRuleEvaluator {
	private final LiturgyRuleRegistry registry;

	public LiturgyRuleEvaluator(LiturgyRuleRegistry registry) {
		this.registry = registry; 
	}

	public List<LiturgicalFeast> evaluate(int liturgicalYear) throws Exception {
		// compute liturgical year start: first Sunday of Advent of previous calendar
		// year
		LocalDate advStart = computeFirstSundayOfAdvent(liturgicalYear - 1);
		LiturgyRuleContext ctx = new LiturgyRuleContext(liturgicalYear, advStart);

		// Build dependency graph based on referencedRuleIds
		Map<String, Set<String>> deps = new HashMap<>(); // node -> set of bases it depends on
		for (LiturgyRule r : registry.all())
			deps.put(r.getId(), new HashSet<>(r.getComputus().referencedRuleIds()));

		// Topological sort
		List<String> order = topoSort(deps);

		// Evaluate in order
		List<LiturgicalFeast> out = new ArrayList<>();
		
		for (String ruleId : order) {
			System.out.println();
			LiturgyRule rule = registry.get(ruleId);
			LocalDate d = rule.getComputus().compute(liturgicalYear+rule.getLiturgicalYearShift(), ctx, registry);
			registry.setComputedDate(ruleId, d);
			out.add(new LiturgicalFeast(rule.getId(), rule.getName(), d, rule.getId()));
		}

		// Sort by date for convenience
		// Funcionan las tres
		out.sort(Comparator.comparing(f -> f.getDate()));
//		out.sort(Comparator.comparing((LiturgicalFeast f) -> f.getDate()));
//		out.sort(Comparator.comparing(LiturgicalFeast::getDate));
		return out;
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

	// First Sunday of Advent: anchor Nov 30, then onOrNext Sunday
	private static LocalDate computeFirstSundayOfAdvent(int calendarYear) {
		LocalDate anchor = LocalDate.of(calendarYear, Month.NOVEMBER, 30);
		if (anchor.getDayOfWeek() == DayOfWeek.SUNDAY)
			return anchor;
		return anchor.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
	}
}
